package peaksoft.servicies.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.GroupRepository;
import peaksoft.servicies.GroupService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public SimpleResponse saveGroup(GroupRequest groupRequest) {
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        groupRepository.save(group);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Group by name is: %s is successfully saved.",groupRequest.getGroupName()))
                .build();
    }

    @Override
    public GroupResponse findByGroupId(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("Group with id " + groupId + " is not found!"));
        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getImageLink(),
                group.getDescription()
        );
    }

    @Override
    public List<GroupResponse> findAllGroups() {
        return groupRepository.getAllGroups();
    }

    @Override
    public SimpleResponse updateGroup(Long groupId, GroupRequest groupRequest) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("Group with id " + groupId + " is not found!"));
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        groupRepository.save(group);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Group by name is: %s is successfully updated.",groupRequest.getGroupName()))
                .build();
    }

    @Override
    public SimpleResponse deleteGroup(Long groupId) {
        if (!groupRepository.existsById(groupId)){
            throw new NoSuchElementException("Group with id " + groupId + " is not found!");
        }
        groupRepository.deleteById(groupId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Group by id is: %s is successfully deleted!",groupId))
                .build();
    }

    @Override
    public SimpleResponse assignGroupToCourse(Long groupId, Long courseId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("Group with id " + groupId + " is not found!"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course with id " + courseId + " is not found!"));
        group.addCourse(course);
        course.addGroup(group);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message(String.format("Group name %s and Course name is: %s is successfully assigned!",group.getGroupName(),course.getCourseName()))
                .build();

    }
}
