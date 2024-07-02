package su.boot.begin.mybatis.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import su.boot.begin.mybatis.service.EmpService;
import su.boot.begin.mybatis.vo.EmpVO;

@Controller
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping("/search")
    // 주어진 검색 조건과 페이지 번호에 따라 직원 목록을 검색하여 모델에 추가한다.
    public String searchEmployeeAll(@RequestParam(name = "searchFilter", required = false) String searchFilter, 
                                    @RequestParam(name = "searchQuery", required = false) String searchQuery, 
                                    @RequestParam(name = "page", defaultValue = "1") int page, 
                                    Model model) {
        // 주어진 검색 조건과 페이지 번호에 따라 직원 목록을 검색한다.
        List<EmpVO> employees = empService.searchEmployee(searchFilter, searchQuery, page); 
        
        // 주어진 검색 조건에 따라 총 페이지 수를 계산힌다.
        int totalPages = empService.totalPage(searchFilter, searchQuery);
        
        // 검색된 직원 목록, 현재 페이지 번호, 검색 기준 필드의 이름, 실제 검색어, 총 페이지 수를 모델에 추가한다.
        model.addAttribute("employees", employees); 
        model.addAttribute("page", page); 
        model.addAttribute("searchFilter", searchFilter); 
        model.addAttribute("searchQuery", searchQuery); 
        model.addAttribute("totalPages", totalPages); 
        
        return "./emp/employees";
    } 
}