package lk.nimal_stores.asset.employee.service;


import lk.nimal_stores.asset.common_asset.model.FileInfo;
import lk.nimal_stores.asset.employee.controller.EmployeeController;
import lk.nimal_stores.asset.employee.dao.EmployeeFilesDao;
import lk.nimal_stores.asset.employee.entity.Employee;
import lk.nimal_stores.asset.employee.entity.EmployeeFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

@Service
@CacheConfig(cacheNames = "employeeFiles")
public class EmployeeFilesService {
    private final EmployeeFilesDao employeeFilesDao;

    @Autowired
    public EmployeeFilesService(EmployeeFilesDao employeeFilesDao) {
        this.employeeFilesDao = employeeFilesDao;
    }

    public EmployeeFiles findByName(String filename) {
        return employeeFilesDao.findByName(filename);
    }

    public void persist(EmployeeFiles storedFile) {
        employeeFilesDao.save(storedFile);
    }


    public List<EmployeeFiles> search(EmployeeFiles employeeFiles) {
        ExampleMatcher matcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<EmployeeFiles> employeeFilesExample = Example.of(employeeFiles, matcher);
        return employeeFilesDao.findAll(employeeFilesExample);
    }

    public EmployeeFiles findById(Integer id) {
        return employeeFilesDao.getOne(id);
    }

    public EmployeeFiles findByNewID(String filename) {
        return employeeFilesDao.findByNewId(filename);
    }


    public FileInfo employeeFileDownloadLinks(Employee employee) {
        EmployeeFiles employeeFiles = employeeFilesDao.findByEmployee(employee);
        if (employeeFiles != null) {
            String filename = employeeFiles.getName();
            String url = MvcUriComponentsBuilder
                .fromMethodName(EmployeeController.class, "downloadFile", employeeFiles.getNewId())
                .build()
                .toString();
            return new FileInfo(filename, employeeFiles.getCreatedAt(), url);
        }
        return null;
    }

    public EmployeeFiles findByEmployee(Employee employee) {
        return employeeFilesDao.findByEmployee(employee);
    }
}
