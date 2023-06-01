package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.servicies.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupApi {
    private final GroupService groupService;
    @Autowired
    public GroupApi(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public SimpleResponse saveGroup(@RequestBody GroupRequest groupRequest){
        return groupService.saveGroup(groupRequest);
    }
    @GetMapping
    public List<GroupResponse> getAllGroups(){
        return groupService.findAllGroups();
    }
    @GetMapping("/{groupId}")
    public GroupResponse getByIdGroup(@PathVariable Long groupId){
        return groupService.findByGroupId(groupId);
    }

    @PutMapping("/{groupId}")
    public SimpleResponse updateGroup(@PathVariable Long groupId,@RequestBody GroupRequest groupRequest){
        return groupService.updateGroup(groupId,groupRequest);
    }
    @DeleteMapping("/{groupId}")
    public SimpleResponse deleteGroup(@PathVariable Long groupId){
        return groupService.deleteGroup(groupId);
    }
    @PostMapping("/{groupId}/{courseId}")
    public SimpleResponse assignGroupToCourse(@PathVariable Long groupId, @PathVariable Long courseId){
        return groupService.assignGroupToCourse(groupId,courseId);
    }
}
