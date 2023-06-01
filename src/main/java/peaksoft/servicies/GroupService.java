package peaksoft.servicies;


import peaksoft.dto.request.GroupRequest;

import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface GroupService {
    SimpleResponse saveGroup(GroupRequest groupRequest);
    GroupResponse findByGroupId(Long groupId);
    List<GroupResponse> findAllGroups();
    SimpleResponse updateGroup(Long groupId,GroupRequest groupRequest);
    SimpleResponse deleteGroup(Long groupId);
    SimpleResponse assignGroupToCourse(Long groupId,Long courseId);
}
