package fr.benco11.jlibecoledirecte.lib.account;

import fr.benco11.jlibecoledirecte.api.account.AccountType;
import fr.benco11.jlibecoledirecte.api.account.EcoleDirecteModule;
import fr.benco11.jlibecoledirecte.api.account.PersonalDetails;
import fr.benco11.jlibecoledirecte.api.account.UserProfile;
import fr.benco11.jlibecoledirecte.api.session.SessionContext;
import fr.benco11.jlibecoledirecte.lib.account.dto.AccountDto;
import fr.benco11.jlibecoledirecte.lib.account.dto.ModuleDto;
import fr.benco11.jlibecoledirecte.lib.account.dto.SettingsDto;
import fr.benco11.jlibecoledirecte.lib.account.factory.AccountFactory;
import fr.benco11.jlibecoledirecte.lib.exception.runtime.EcoleDirecteMappingException;
import fr.benco11.jlibecoledirecte.lib.utils.HttpService;
import java.net.MalformedURLException;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

    default BasicAccount accountDtoAndSettingsDtoToDefaultAccount(
            AccountDto account,
            SettingsDto settings,
            String password,
            AccountFactory accountFactory,
            SessionContext context,
            HttpService httpService) {
        try {
            AccountType   accountType =
                    AccountType.accountType(account.typeCompte()).orElseThrow(EcoleDirecteMappingException::new);
            List<EcoleDirecteModule> modules = account.modules().stream()
                    .map(dto -> (EcoleDirecteModule) moduleDtoToModule(dto))
                    .toList();
            PersonalDetails personalDetails = accountDtoToPersonalDetails(account);
            UserProfile userProfile = accountDtoAndSettingsDtoToUserProfile(account, settings, password);
            return accountFactory.getAccount(
                    new AccountData(accountType, modules, personalDetails, userProfile), context, httpService);
        } catch (MalformedURLException exception) {
            throw new EcoleDirecteMappingException();
        }
    }

    @Mapping(target = "enabled", source = "enable")
    @Mapping(target = "order", source = "ordre")
    DefaultEcoleDirecteModule moduleDtoToModule(ModuleDto module);

    @Mapping(target = "firstName", source = "prenom")
    @Mapping(target = "lastName", source = "nom")
    @Mapping(target = "sex", source = "profile.sexe")
    @Mapping(target = "schoolName", source = "nomEtablissement")
    @Mapping(target = "currentSchoolYear", source = "anneeScolaireCourante")
    @Mapping(target = "className", source = "profile.classe.libelle")
    @Mapping(target = "classCode", source = "profile.classe.code")
    DefaultPersonalDetails accountDtoToPersonalDetails(AccountDto account);

    @Mapping(target = "id", source = "account.id")
    @Mapping(target = "email", source = "account.email")
    @Mapping(target = "username", source = "account.identifiant")
    @Mapping(target = "phone", source = "settings.portable")
    @Mapping(
            target = "pictureUrl",
            expression =
                    "java(fr.benco11.jlibecoledirecte.lib.utils.HttpService.HttpProtocol.HTTPS.getUrl(account.profile"
                            + "().photo().replaceAll(\"^//\", \"\")))")
    DefaultUserProfile accountDtoAndSettingsDtoToUserProfile(AccountDto account, SettingsDto settings, String password)
            throws MalformedURLException;
}
