package  ma.zyn.app.ws.facade.admin.commande;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.commande.CommandeItem;
import ma.zyn.app.dao.criteria.core.commande.CommandeItemCriteria;
import ma.zyn.app.service.facade.admin.commande.CommandeItemAdminService;
import ma.zyn.app.ws.converter.commande.CommandeItemConverter;
import ma.zyn.app.ws.dto.commande.CommandeItemDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/commandeItem/")
public class CommandeItemRestAdmin {





    @Operation(summary = "Finds a list of all commandeItems")
    @GetMapping("")
    public ResponseEntity<List<CommandeItemDto>> findAll() throws Exception {
        ResponseEntity<List<CommandeItemDto>> res = null;
        List<CommandeItem> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<CommandeItemDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a commandeItem by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CommandeItemDto> findById(@PathVariable Long id) {
        CommandeItem t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CommandeItemDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  commandeItem")
    @PostMapping("")
    public ResponseEntity<CommandeItemDto> save(@RequestBody CommandeItemDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            CommandeItem myT = converter.toItem(dto);
            CommandeItem t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CommandeItemDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  commandeItem")
    @PutMapping("")
    public ResponseEntity<CommandeItemDto> update(@RequestBody CommandeItemDto dto) throws Exception {
        ResponseEntity<CommandeItemDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CommandeItem t = service.findById(dto.getId());
            converter.copy(dto,t);
            CommandeItem updated = service.update(t);
            CommandeItemDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of commandeItem")
    @PostMapping("multiple")
    public ResponseEntity<List<CommandeItemDto>> delete(@RequestBody List<CommandeItemDto> dtos) throws Exception {
        ResponseEntity<List<CommandeItemDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<CommandeItem> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified commandeItem")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by commande id")
    @GetMapping("commande/id/{id}")
    public List<CommandeItemDto> findByCommandeId(@PathVariable Long id){
        return findDtos(service.findByCommandeId(id));
    }
    @Operation(summary = "delete by commande id")
    @DeleteMapping("commande/id/{id}")
    public int deleteByCommandeId(@PathVariable Long id){
        return service.deleteByCommandeId(id);
    }

    @Operation(summary = "Finds a commandeItem and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CommandeItemDto> findWithAssociatedLists(@PathVariable Long id) {
        CommandeItem loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CommandeItemDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds commandeItems by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CommandeItemDto>> findByCriteria(@RequestBody CommandeItemCriteria criteria) throws Exception {
        ResponseEntity<List<CommandeItemDto>> res = null;
        List<CommandeItem> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CommandeItemDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated commandeItems by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CommandeItemCriteria criteria) throws Exception {
        List<CommandeItem> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<CommandeItemDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets commandeItem data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CommandeItemCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CommandeItemDto> findDtos(List<CommandeItem> list){
        converter.initObject(true);
        List<CommandeItemDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CommandeItemDto> getDtoResponseEntity(CommandeItemDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CommandeItemRestAdmin(CommandeItemAdminService service, CommandeItemConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CommandeItemAdminService service;
    private final CommandeItemConverter converter;





}
