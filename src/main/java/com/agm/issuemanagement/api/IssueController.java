package com.agm.issuemanagement.api;

import com.agm.issuemanagement.dto.IssueDetailDto;
import com.agm.issuemanagement.dto.IssueDto;
import com.agm.issuemanagement.service.impl.IssueServiceImpl;
import com.agm.issuemanagement.util.ApiPaths;
import com.agm.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL, description = "Issue APIs")
@CrossOrigin
public class IssueController {

    private final IssueServiceImpl issueServiceImpl;

    public IssueController(IssueServiceImpl issueServiceImpl) {
        this.issueServiceImpl = issueServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = IssueDto.class)
    public ResponseEntity<TPage<IssueDto>> getAllByPagination(Pageable pageable) {
        TPage<IssueDto> data = issueServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
    public ResponseEntity<IssueDto> getById(@PathVariable(value = "id", required = true) Long id) {
        IssueDto issueDto = issueServiceImpl.getById(id);
        return ResponseEntity.ok(issueDto);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Get By Id Operation", response = IssueDetailDto.class)
    public ResponseEntity<IssueDetailDto> getByIdWithDetails(@PathVariable(value = "id", required = true) Long id){
        IssueDetailDto detailDto = issueServiceImpl.getByIdWithDetails(id);
        return ResponseEntity.ok(detailDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = IssueDto.class)
    public ResponseEntity<IssueDto> createIssue(@Valid @RequestBody IssueDto issue) {
        return ResponseEntity.ok(issueServiceImpl.save(issue));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = IssueDto.class)
    public ResponseEntity<IssueDto> updateIssue(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody IssueDto issue) {
        return ResponseEntity.ok(issueServiceImpl.update(id, issue));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(issueServiceImpl.delete(id));
    }
}
