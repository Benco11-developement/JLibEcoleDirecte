package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.account.AccountType;
import fr.benco11.jlibecoledirecte.api.account.EcoleDirecteModule;
import fr.benco11.jlibecoledirecte.api.account.PersonalDetails;
import fr.benco11.jlibecoledirecte.api.account.UserProfile;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.account.dto.AccountDTO;
import fr.benco11.jlibecoledirecte.lib.account.dto.ModuleDTO;
import fr.benco11.jlibecoledirecte.lib.account.dto.SettingsDTO;
import fr.benco11.jlibecoledirecte.lib.exception.runtime.EcoleDirecteMappingException;
import fr.benco11.jlibecoledirecte.lib.account.factory.AccountFactory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.net.MalformedURLException;
import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

    default BasicAccount accountDTOAndSettingsDTOToDefaultAccount(
            AccountDTO accountDTO, SettingsDTO settingsDTO, String password,
            AccountFactory accountFactory, SessionContext context) {
        try {
            AccountType accountType = AccountType.accountType(accountDTO.typeCompte())
                    .orElseThrow(EcoleDirecteMappingException::new);
            List<EcoleDirecteModule> modules = accountDTO.modules()
                    .stream()
                    .map(dto -> (EcoleDirecteModule) moduleDTOToModule(dto))
                    .toList();
            PersonalDetails personalDetails = accountDTOToPersonalDetails(accountDTO);
            UserProfile userProfile = accountDTOAndSettingsDTOToUserProfile(accountDTO, settingsDTO, password);
            return accountFactory.getAccount(accountType, modules, personalDetails, userProfile, context);
        } catch (MalformedURLException exception) {
            throw new EcoleDirecteMappingException();
        }
    }

    @Mapping(target = "enabled", source = "enable")
    @Mapping(target = "order", source = "ordre")
    DefaultEcoleDirecteModule moduleDTOToModule(ModuleDTO moduleDTO);

    @Mapping(target = "firstName", source = "prenom")
    @Mapping(target = "lastName", source = "nom")
    @Mapping(target = "sex", source = "profile.sexe")
    @Mapping(target = "schoolName", source = "nomEtablissement")
    @Mapping(target = "currentSchoolYear", source = "anneeScolaireCourante")
    @Mapping(target = "className", source = "profile.classe.libelle")
    @Mapping(target = "classCode", source = "profile.classe.code")
    DefaultPersonalDetails accountDTOToPersonalDetails(AccountDTO accountDTO);


    @Mapping(target = "id", source = "accountDTO.id")
    @Mapping(target = "email", source = "accountDTO.email")
    @Mapping(target = "username", source = "accountDTO.identifiant")
    @Mapping(target = "phone", source = "settingsDTO.portable")
    @Mapping(target = "pictureURL", expression = "java(fr.benco11.jlibecoledirecte.lib.utils.HttpUtils.HttpProtocol.HTTPS.getURL(accountDTO.profile().photo().replaceAll(\"^//\", \"\")))")
    DefaultUserProfile accountDTOAndSettingsDTOToUserProfile(AccountDTO accountDTO,
                                                             SettingsDTO settingsDTO, String password) throws MalformedURLException;
}
