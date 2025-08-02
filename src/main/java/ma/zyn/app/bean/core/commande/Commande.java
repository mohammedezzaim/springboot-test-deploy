package ma.zyn.app.bean.core.commande;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.commun.EtatCommande;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "commande")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="commande_seq",sequenceName="commande_seq",allocationSize=1, initialValue = 1)
public class Commande  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    private BigDecimal total = BigDecimal.ZERO;

    private BigDecimal totalPayer = BigDecimal.ZERO;

    private LocalDateTime dateCommande ;

    private EtatCommande etatComande ;

    private List<CommandeItem> commandeItems ;

    public Commande(){
        super();
    }

    public Commande(Long id){
        this.id = id;
    }

    public Commande(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public Commande(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="commande_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public BigDecimal getTotal(){
        return this.total;
    }
    public void setTotal(BigDecimal total){
        this.total = total;
    }
    public BigDecimal getTotalPayer(){
        return this.totalPayer;
    }
    public void setTotalPayer(BigDecimal totalPayer){
        this.totalPayer = totalPayer;
    }
    public LocalDateTime getDateCommande(){
        return this.dateCommande;
    }
    public void setDateCommande(LocalDateTime dateCommande){
        this.dateCommande = dateCommande;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etat_comande")
    public EtatCommande getEtatComande(){
        return this.etatComande;
    }
    public void setEtatComande(EtatCommande etatComande){
        this.etatComande = etatComande;
    }
    @OneToMany(mappedBy = "commande")
    public List<CommandeItem> getCommandeItems(){
        return this.commandeItems;
    }

    public void setCommandeItems(List<CommandeItem> commandeItems){
        this.commandeItems = commandeItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return id != null && id.equals(commande.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

