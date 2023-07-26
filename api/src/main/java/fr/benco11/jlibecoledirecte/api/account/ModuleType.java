package fr.benco11.jlibecoledirecte.api.account;

import java.util.Arrays;
import java.util.Optional;

public enum ModuleType {
    CANTEEN_BAR("CANTINE_BARCODE"),
    SCHOOL_LIFE("VIE_SCOLAIRE"),
    CLASS_LIFE("VIE_DE_LA_CLASSE"),
    GRADES("NOTES"),
    CLOUD("CLOUD"),
    MESSAGING_SERVICE("MESSAGERIE"),
    SCHEDULE("EDT"),
    STUDENT_DOCUMENTS("DOCUMENTS_ELEVE"),
    HOMEWORKS("CAHIER_DE_TEXTES"),
    TEXTBOOK("MANUELS_SCOLAIRES"),
    BOOKING("RESERVATIONS"),
    ORDER("COMMANDE_PASSAGE"),
    PARENT_TEACHER_CONTACT("CARNET_CORRESPONDANCE"),
    ESIDOC("ESIDOC"),
    EDUANO("EDUANO"),
    CATER("CATER"),
    ARD("ARD"),
    PEARLTREES("PEARLTREES"),
    EDUMALIN("EDUMALIN"),
    STAGE_MONITORING("SUIVI_STAGE"),
    CLICKNPLAY("CLICKNPLAY"),
    VOLTAIRE("VOLAITRE"),
    AVENRIA("AVENRIA"),
    BAG("SACOCHE"),
    STUDENT("ETUDIANT"),
    IJBOX("IJBOX"),
    FUTURNESS("FUTURNESS"),
    IMPALA("IMPALA"),
    POPLAB("POPLAB"),
    EDUMEDIA("EDUMEDIA"),
    FINANCIAL_SITUATION("SITUATION_FINANCIERE"),
    EDIALOG("EDIALOG");
    private final String code;

    ModuleType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public static Optional<ModuleType> type(EcoleDirecteModule module) {
        return Arrays.stream(values())
                .filter(type -> type.code.equalsIgnoreCase(module.code()))
                .findAny();
    }
}
