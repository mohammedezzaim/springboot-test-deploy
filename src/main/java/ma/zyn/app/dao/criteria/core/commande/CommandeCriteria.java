package  ma.zyn.app.dao.criteria.core.commande;


import ma.zyn.app.dao.criteria.core.commun.EtatCommandeCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CommandeCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String total;
    private String totalMin;
    private String totalMax;
    private String totalPayer;
    private String totalPayerMin;
    private String totalPayerMax;
    private LocalDateTime dateCommande;
    private LocalDateTime dateCommandeFrom;
    private LocalDateTime dateCommandeTo;

    private EtatCommandeCriteria etatComande ;
    private List<EtatCommandeCriteria> etatComandes ;


    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getTotal(){
        return this.total;
    }
    public void setTotal(String total){
        this.total = total;
    }   
    public String getTotalMin(){
        return this.totalMin;
    }
    public void setTotalMin(String totalMin){
        this.totalMin = totalMin;
    }
    public String getTotalMax(){
        return this.totalMax;
    }
    public void setTotalMax(String totalMax){
        this.totalMax = totalMax;
    }
      
    public String getTotalPayer(){
        return this.totalPayer;
    }
    public void setTotalPayer(String totalPayer){
        this.totalPayer = totalPayer;
    }   
    public String getTotalPayerMin(){
        return this.totalPayerMin;
    }
    public void setTotalPayerMin(String totalPayerMin){
        this.totalPayerMin = totalPayerMin;
    }
    public String getTotalPayerMax(){
        return this.totalPayerMax;
    }
    public void setTotalPayerMax(String totalPayerMax){
        this.totalPayerMax = totalPayerMax;
    }
      
    public LocalDateTime getDateCommande(){
        return this.dateCommande;
    }
    public void setDateCommande(LocalDateTime dateCommande){
        this.dateCommande = dateCommande;
    }
    public LocalDateTime getDateCommandeFrom(){
        return this.dateCommandeFrom;
    }
    public void setDateCommandeFrom(LocalDateTime dateCommandeFrom){
        this.dateCommandeFrom = dateCommandeFrom;
    }
    public LocalDateTime getDateCommandeTo(){
        return this.dateCommandeTo;
    }
    public void setDateCommandeTo(LocalDateTime dateCommandeTo){
        this.dateCommandeTo = dateCommandeTo;
    }

    public EtatCommandeCriteria getEtatComande(){
        return this.etatComande;
    }

    public void setEtatComande(EtatCommandeCriteria etatComande){
        this.etatComande = etatComande;
    }
    public List<EtatCommandeCriteria> getEtatComandes(){
        return this.etatComandes;
    }

    public void setEtatComandes(List<EtatCommandeCriteria> etatComandes){
        this.etatComandes = etatComandes;
    }
}
