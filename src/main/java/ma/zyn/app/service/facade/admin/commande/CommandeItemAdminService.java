package ma.zyn.app.service.facade.admin.commande;

import java.util.List;
import ma.zyn.app.bean.core.commande.CommandeItem;
import ma.zyn.app.dao.criteria.core.commande.CommandeItemCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CommandeItemAdminService {



    List<CommandeItem> findByCommandeId(Long id);
    int deleteByCommandeId(Long id);
    long countByCommandeCode(String code);




	CommandeItem create(CommandeItem t);

    CommandeItem update(CommandeItem t);

    List<CommandeItem> update(List<CommandeItem> ts,boolean createIfNotExist);

    CommandeItem findById(Long id);

    CommandeItem findOrSave(CommandeItem t);

    CommandeItem findByReferenceEntity(CommandeItem t);

    CommandeItem findWithAssociatedLists(Long id);

    List<CommandeItem> findAllOptimized();

    List<CommandeItem> findAll();

    List<CommandeItem> findByCriteria(CommandeItemCriteria criteria);

    List<CommandeItem> findPaginatedByCriteria(CommandeItemCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CommandeItemCriteria criteria);

    List<CommandeItem> delete(List<CommandeItem> ts);

    boolean deleteById(Long id);

    List<List<CommandeItem>> getToBeSavedAndToBeDeleted(List<CommandeItem> oldList, List<CommandeItem> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
