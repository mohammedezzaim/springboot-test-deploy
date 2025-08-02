package  ma.zyn.app.ws.converter.commande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.commande.CommandeConverter;
import ma.zyn.app.bean.core.commande.Commande;

import ma.zyn.app.bean.core.commande.Commande;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.commande.CommandeItem;
import ma.zyn.app.ws.dto.commande.CommandeItemDto;

@Component
public class CommandeItemConverter {

    @Autowired
    private CommandeConverter commandeConverter ;
    private boolean commande;

    public  CommandeItemConverter() {
        initObject(true);
    }

    public CommandeItem toItem(CommandeItemDto dto) {
        if (dto == null) {
            return null;
        } else {
        CommandeItem item = new CommandeItem();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getPrix()))
                item.setPrix(dto.getPrix());
            if(StringUtil.isNotEmpty(dto.getQuantite()))
                item.setQuantite(dto.getQuantite());
            if(dto.getCommande() != null && dto.getCommande().getId() != null){
                item.setCommande(new Commande());
                item.getCommande().setId(dto.getCommande().getId());
                item.getCommande().setCode(dto.getCommande().getCode());
            }




        return item;
        }
    }


    public CommandeItemDto toDto(CommandeItem item) {
        if (item == null) {
            return null;
        } else {
            CommandeItemDto dto = new CommandeItemDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getPrix()))
                dto.setPrix(item.getPrix());
            if(StringUtil.isNotEmpty(item.getQuantite()))
                dto.setQuantite(item.getQuantite());
            if(this.commande && item.getCommande()!=null) {
                dto.setCommande(commandeConverter.toDto(item.getCommande())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.commande = value;
    }
	
    public List<CommandeItem> toItem(List<CommandeItemDto> dtos) {
        List<CommandeItem> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CommandeItemDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CommandeItemDto> toDto(List<CommandeItem> items) {
        List<CommandeItemDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CommandeItem item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CommandeItemDto dto, CommandeItem t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCommande() == null  && dto.getCommande() != null){
            t.setCommande(new Commande());
        }else if (t.getCommande() != null  && dto.getCommande() != null){
            t.setCommande(null);
            t.setCommande(new Commande());
        }
        if (dto.getCommande() != null)
        commandeConverter.copy(dto.getCommande(), t.getCommande());
    }

    public List<CommandeItem> copy(List<CommandeItemDto> dtos) {
        List<CommandeItem> result = new ArrayList<>();
        if (dtos != null) {
            for (CommandeItemDto dto : dtos) {
                CommandeItem instance = new CommandeItem();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CommandeConverter getCommandeConverter(){
        return this.commandeConverter;
    }
    public void setCommandeConverter(CommandeConverter commandeConverter ){
        this.commandeConverter = commandeConverter;
    }
    public boolean  isCommande(){
        return this.commande;
    }
    public void  setCommande(boolean commande){
        this.commande = commande;
    }
}
