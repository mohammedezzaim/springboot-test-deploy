package  ma.zyn.app.dao.specification.core.commande;

import ma.zyn.app.dao.criteria.core.commande.CommandeItemCriteria;
import ma.zyn.app.bean.core.commande.CommandeItem;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CommandeItemSpecification extends  AbstractSpecification<CommandeItemCriteria, CommandeItem>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("prix", criteria.getPrix(), criteria.getPrixMin(), criteria.getPrixMax());
        addPredicateBigDecimal("quantite", criteria.getQuantite(), criteria.getQuantiteMin(), criteria.getQuantiteMax());
        addPredicateFk("commande","id", criteria.getCommande()==null?null:criteria.getCommande().getId());
        addPredicateFk("commande","id", criteria.getCommandes());
        addPredicateFk("commande","code", criteria.getCommande()==null?null:criteria.getCommande().getCode());
    }

    public CommandeItemSpecification(CommandeItemCriteria criteria) {
        super(criteria);
    }

    public CommandeItemSpecification(CommandeItemCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
