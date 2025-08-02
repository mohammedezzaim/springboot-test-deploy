package ma.zyn.app.service.facade.admin.commande;

import java.util.List;
import ma.zyn.app.bean.core.commande.Commande;
import ma.zyn.app.dao.criteria.core.commande.CommandeCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CommandeAdminService {



    List<Commande> findByEtatComandeCode(String code);
    List<Commande> findByEtatComandeId(Long id);
    int deleteByEtatComandeId(Long id);
    int deleteByEtatComandeCode(String code);
    long countByEtatComandeCode(String code);




	Commande create(Commande t);

    Commande update(Commande t);

    List<Commande> update(List<Commande> ts,boolean createIfNotExist);

    Commande findById(Long id);

    Commande findOrSave(Commande t);

    Commande findByReferenceEntity(Commande t);

    Commande findWithAssociatedLists(Long id);

    List<Commande> findAllOptimized();

    List<Commande> findAll();

    List<Commande> findByCriteria(CommandeCriteria criteria);

    List<Commande> findPaginatedByCriteria(CommandeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CommandeCriteria criteria);

    List<Commande> delete(List<Commande> ts);

    boolean deleteById(Long id);

    List<List<Commande>> getToBeSavedAndToBeDeleted(List<Commande> oldList, List<Commande> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
