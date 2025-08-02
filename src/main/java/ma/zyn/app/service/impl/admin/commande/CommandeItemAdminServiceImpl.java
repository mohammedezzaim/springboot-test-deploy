package ma.zyn.app.service.impl.admin.commande;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.commande.CommandeItem;
import ma.zyn.app.dao.criteria.core.commande.CommandeItemCriteria;
import ma.zyn.app.dao.facade.core.commande.CommandeItemDao;
import ma.zyn.app.dao.specification.core.commande.CommandeItemSpecification;
import ma.zyn.app.service.facade.admin.commande.CommandeItemAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.commande.CommandeAdminService ;
import ma.zyn.app.bean.core.commande.Commande ;

import java.util.List;
@Service
public class CommandeItemAdminServiceImpl implements CommandeItemAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CommandeItem update(CommandeItem t) {
        CommandeItem loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CommandeItem.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CommandeItem findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CommandeItem findOrSave(CommandeItem t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            CommandeItem result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CommandeItem> findAll() {
        return dao.findAll();
    }

    public List<CommandeItem> findByCriteria(CommandeItemCriteria criteria) {
        List<CommandeItem> content = null;
        if (criteria != null) {
            CommandeItemSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CommandeItemSpecification constructSpecification(CommandeItemCriteria criteria) {
        CommandeItemSpecification mySpecification =  (CommandeItemSpecification) RefelexivityUtil.constructObjectUsingOneParam(CommandeItemSpecification.class, criteria);
        return mySpecification;
    }

    public List<CommandeItem> findPaginatedByCriteria(CommandeItemCriteria criteria, int page, int pageSize, String order, String sortField) {
        CommandeItemSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CommandeItemCriteria criteria) {
        CommandeItemSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<CommandeItem> findByCommandeId(Long id){
        return dao.findByCommandeId(id);
    }
    public int deleteByCommandeId(Long id){
        return dao.deleteByCommandeId(id);
    }
    public long countByCommandeCode(String code){
        return dao.countByCommandeCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CommandeItem> delete(List<CommandeItem> list) {
		List<CommandeItem> result = new ArrayList();
        if (list != null) {
            for (CommandeItem t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CommandeItem create(CommandeItem t) {
        CommandeItem loaded = findByReferenceEntity(t);
        CommandeItem saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CommandeItem findWithAssociatedLists(Long id){
        CommandeItem result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CommandeItem> update(List<CommandeItem> ts, boolean createIfNotExist) {
        List<CommandeItem> result = new ArrayList<>();
        if (ts != null) {
            for (CommandeItem t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CommandeItem loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CommandeItem t, CommandeItem loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CommandeItem findByReferenceEntity(CommandeItem t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(CommandeItem t){
        if( t != null) {
            t.setCommande(commandeService.findOrSave(t.getCommande()));
        }
    }



    public List<CommandeItem> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<CommandeItem>> getToBeSavedAndToBeDeleted(List<CommandeItem> oldList, List<CommandeItem> newList) {
        List<List<CommandeItem>> result = new ArrayList<>();
        List<CommandeItem> resultDelete = new ArrayList<>();
        List<CommandeItem> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<CommandeItem> oldList, List<CommandeItem> newList, List<CommandeItem> resultUpdateOrSave, List<CommandeItem> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CommandeItem myOld = oldList.get(i);
                CommandeItem t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CommandeItem myNew = newList.get(i);
                CommandeItem t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private CommandeAdminService commandeService ;

    public CommandeItemAdminServiceImpl(CommandeItemDao dao) {
        this.dao = dao;
    }

    private CommandeItemDao dao;
}
