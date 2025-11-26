package ma.formations.spring.rest.service;

import lombok.AllArgsConstructor;
import ma.formations.spring.rest.dao.EmpRepository;
import ma.formations.spring.rest.domaine.EmpVo;
import ma.formations.spring.rest.service.modele.Emp;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@AllArgsConstructor
public class ServiceImpl implements IService {
    private EmpRepository empRepository;
    private ModelMapper modelMapper;
    @Override
    public List<EmpVo> findEmployeesByName(String name) {
        List<Emp> list = empRepository.findByFirstName(name);
        return list.stream().map(bo -> modelMapper.map(bo, EmpVo.class)).toList();
    }
    @Override
    public List<EmpVo> getEmployees() {
        List<Emp> list = empRepository.findAll();
        return list.stream().map(bo -> modelMapper.map(bo, EmpVo.class)).toList();
    }
    @Transactional
    @Override
    public void save(EmpVo vo) {
        empRepository.save(modelMapper.map(vo, Emp.class));
    }
    @Override
    public EmpVo getEmpById(Long id) {
        boolean trouve = empRepository.existsById(id);
        if (!trouve)
            return null;
        return modelMapper.map(empRepository.getOne(id), EmpVo.class);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        empRepository.deleteById(id);
    }
    @Override
    public List<EmpVo> findBySalary(Double salaty) {
        List<Emp> list = empRepository.findBySalaire(salaty);
        return list.stream().map(bo -> modelMapper.map(bo, EmpVo.class)).toList();
    }
    @Override
    public List<EmpVo> findByFonction(String fonction) {
        List<Emp> list = empRepository.findByFonction(fonction);
        return list.stream().map(bo -> modelMapper.map(bo, EmpVo.class)).toList();
    }
    @Override
    public List<EmpVo> findBySalaryAndFonction(Double salary, String fonction) {
        List<Emp> list = empRepository.findBySalaireAndFonction(salary, fonction);
        return list.stream().map(bo -> modelMapper.map(bo, EmpVo.class)).toList();
    }
    @Override
    public EmpVo getEmpHavaingMaxSalary() {
        return modelMapper.map(empRepository.getEmpHavaingMaxSalary(), EmpVo.class);
    }
    @Override
    public List<EmpVo> findAll(int pageId, int size) {
        Page<Emp> result = empRepository.findAll(PageRequest.of(pageId, size, Sort.by("salaire")));
        return result.getContent().stream().map(bo -> modelMapper.map(bo, EmpVo.class)).toList();
    }
    @Override
    public List<EmpVo> sortBy(String... fieldNames) {
        return Sort.by(fieldNames).stream().map(bo -> modelMapper.map(bo, EmpVo.class)).toList();
    }
    @Transactional
    @Override
    public void deleteAll() {
        empRepository.deleteAll();
    }
}
