import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Thong_Ke_Percent {

    public static final int TOTAL_LETTERS_ALPHABET = 26;
    public static final int ASCII_CODE_OF_A = 65;

    public static String[] initAlphabet() {
        String[] alphabet = new String[TOTAL_LETTERS_ALPHABET];
        int index = 0;
        for (int i = ASCII_CODE_OF_A; i < ASCII_CODE_OF_A + TOTAL_LETTERS_ALPHABET; i++) {
            alphabet[index] = (char) i +"";
            index++;
        }
        return alphabet;
    }

    public static String[] initDsPhuAmKep() {
        String[] alphabet = new String[26*26];
        int index = 0;
        for (int i = ASCII_CODE_OF_A; i < ASCII_CODE_OF_A + TOTAL_LETTERS_ALPHABET; i++) {
            for (int j = ASCII_CODE_OF_A; j < ASCII_CODE_OF_A + TOTAL_LETTERS_ALPHABET; j++) {
                    alphabet[index] = (char) i +""+(char) j;
                index++;
            }
        }
        return alphabet;
    }

    public static LinkedHashMap<String, Double> thongKeTanSoMoiKyTu(String input, String[] elements) {
        long size = 0;
        LinkedHashMap<String, Double> table = new LinkedHashMap<String, Double>();
        // count
        for (String e : elements) {
            Pattern pattern = Pattern.compile(e + "");
            Matcher matcher = pattern.matcher(input);
            long matches = matcher.results().count();
            size+=matches;
            table.put(e, (double) matches );
        }
        // tính %
        for (String e : elements) {
            table.put(e, (double) Math.round(( table.get(e) *1.0)*100/size*10)/10 );
        }
        //sort
        LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();
        table.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        return reverseSortedMap;
    }

    public static String printMap(Map map, int numberLine) {
        StringBuilder str = new StringBuilder();
        int currentLine = 1;
        for (Object ch : map.keySet()) {
            str.append(ch + " ==> " + map.get(ch) + " % \n");
            currentLine++;
            if (currentLine>numberLine){
                break;
            }
        }
        return str.toString();
    }

    public static String xoaDauAndUppercase(String text) {
        text = text.toUpperCase();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("A","[ÀÁẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶ]");
        map.put("E","[ÉÈẺẼẸÊẾỀỂỄỆ]");
        map.put("O","[ÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢ]");
        map.put("U","[ÚÙỦŨỤƯỨỪỬỮỰ]");
        map.put("I","[ÍÌỈĨỊ]");
        map.put("Y","[ÝỲỶỸỴ]");
        map.put("D","Đ");
        for (String key: map.keySet()) {
            text = text.replaceAll(map.get(key),key);
        }
        return text;
    }

    public static void main(String[] args) {
        String input = "\"PB GHO E?\", FCDOBF MGD MGDO UDHO IHFC GEN LGEFC XGN LH REA MJE KH PB GBM. LDEN LDFC GE UDOBM KNFG E WHN KB MNA LE GEN MGHO KEN LEFC PNBL.\n" +
                "\n" +
                "FHA FHA KN WHA HF RH, KHO WH WHF KHD MGDO WDEFC WD. PE LGEFC MGDO WHA QGD GE. HFG FCDOBF MGHFG LE, 32 MDEN, LGEFC MGDO KDEL MJH 300.000 KEFC AEM FCHO LEFC. FD CNEN CNH ZBE GEF, LGN 250.000 KEFC AEM FCHO. \"FHFC AEN LE PNBL, LGD ADH MGN LGHFC LE KEFC FHE\", MGDO XB PB KHL MGD LEFC PNBL.\n" +
                "\n" +
                "LEPNK-19 ZDFC QGHM XGNBF GHN PE LGEFC MGHM FCGNBQ MD KHD MGHFC 6. KH ZH MGHFC FHO GE KH RNF XGHM XGEHF MNBF MJE 1,5 MJNBD KEFC AEN MGHFC. CNH KNFG ZEF FCDEN MJEFC LGE PHE FCDEF WDEFC MGDL KDEL QGHM MD MGNBF PH XGEHF GE MJE LDH LGNFG UDOBF.\n" +
                "\n" +
                "LHF QGEFC MJE LGDH KHO 10 A2 LDH PE LGEFC LGN MGDO PH GHN LEF E MJEFC IDEM 4 MGHFC KNLG. HFG: FGHF PHM LDFC LHQ.\n" +
                "LHF QGEFC MJE LGDH KHO 10 A2 LDH PE LGEFC LGN MGDO PH GHN LEF MHN ZNFG KDEFC GEA 8/10. HFG: FGHF PHM LDFC LHQ.\n" +
                "\n" +
                "MGHO FCDEN MD LHL MNFG KE PB, AB MGDO WNBF MDL CEN KNBF MGEHN CNDL LEF. MJEFC 7 FCHO, GHD CNHFC UDB LE KH KEF GEF 9.000 FCDEN PB UDB. FGDFC LHF MNBF, IE AHFC ZBFG PB UDB, PE LGEFC LE LE ZHA MJD. \"KD E WHN LDFC LGHFC HF MEHF WHN KEN GEF E UDB\", LE MGE KHN. GEF AEM MGHFC MJDEL, LGEFC MGDO WH S0 IHD AEM WHF JH FCEHN.\n" +
                "\n" +
                "FCDEN WEF LE ZDH LEA, ZDH LGHE HF XBA LH XGE, AHA JDEL, FCHF WHN KEN IHFC AN CEN. \"FGDFC ZEF MJB LHF IDH\", MGDO FEN. XGEFC MGB PHO ADEF MNBF, LE MNA LHL FGEA GE MJE RNF IDH LGE LEF. FCHN FGDFC PHE KDEFC LDFC, LE KHFG LHD LDD MJBF AHFC. ZH WHF KN RNF, GHN LEF FGE 4 MDEN PH 2 MDEN KDEL FGHF 350.000 KEFC, LDFC ZHFG IDH LDH FGH GHE MHA. LGNBD 7/10 LEF WHN KEN IDH. IE IDH LD GBM LHLG KHO PHN MDHF, MGDO KB WHN MGEFC MNF XBA IE KNBF MGEHN KDEN ZHN XBD CEN. LH MEN, LE LGHD MJDL KNBF MGEHN, AEFC LE FCDEN WNBF WHL.\n" +
                "\n" +
                "FGNF REA MJE PHFC FCHM E MGN RH MHF DOBF, MNFG ZNFG KDEFC, PE LGEFC MGDO ZNBM ANFG WH IE NM FGDFC FCDEN XGEFC LGND GEH ANFG PHE KEFC FCDEN KE PB LHL MNFG ANBF MHO GEHL JH ZHL FGDFC FCHO UDH. MGBE MGEFC XB LDH ZE LEFC HF, XB MD XGN LHL MNFG QGNH FHA GBM CNHF LHLG MD 1/10, LE XGEHFC 2,1 MJNBD MJEFC MEFC IE 3,5 MJNBD WHE KEFC MHN MQ GLA, ZNFG KDEFC, KEFC FHN PH WEFC HF ADEF PB UDB.\n" +
                "\n" +
                "GHFC FCGNF FCDEN KHF LGE E LGEM XNBA IEHM MJBF UDEL WE 1H, GDOBF ZNFG LGHFG MQ GLA KB LGE PB UDB E LHL MNFG ANBF MHO, FCHO 1/10. HFG: UDOFG MJHF.\n" +
                "GHFC FCGNF FCDEN KHF LGE E LGEM XNBA IEHM MJBF UDEL WE 1H, GDOBF ZNFG LGHFG MQ GLA KB LGE PB UDB E LHL MNFG ANBF MHO, FCHO 1/10. HFG: UDOFG MJHF.\n" +
                "\n" +
                "IEFC MJEFC LHF MJE LGHM GBQ IDEM ZEF MGHFC, FGDFC CNH KNFG MJHF HFG GDEFC, 22 MDEN, MJD MHN GEL AEF XGEFC KNFG PB ZHL WNBD MJHFG KNLG. KB KN, XGE MJE WHN WH FEN WE LGDFC LDH GDEFC PH FGNBD WHE KEFC. \"ANFG LE MGB ZN AHM PNBL FBD XGEFC XNQ UDHO WHN XGN LEFC MO CEN KN WHA\", LE FEN, \"LGDH XB FCDEN E UDB IE KHF IHN CEF PB WHA. WE WHN WH S0\".\n" +
                "\n" +
                "WE WHFC LDH GDEFC XGEFC QGHN PE LHF LD. KBF FCHO 5/10, LHF MGE CGN FGHF 30 MJEFC 54 LH AHL LEPNK-19 AEN WH FCDEN E MNFG XGHL PB; GHD CNHFC LE 19 LH, MJH PNFG 10 LH, ZBF MJB 8 MJEFC LH; KEFC MGHQ LE 60 MJEFC IE 67 LH KBD WH FCDEN GEN GDEFC.\n" +
                "\n" +
                "XGD LHLG WO UDH MHN, AEM IE MNFG LGE FCDEN PB UDB MGBE KEN IDL XGEB MHN FGH, FBD RBM FCGNBA HA MNFG GEHL KH MNBA PHLLNFB. KE MEF XBA, FGDFC GDEFC B FCHN ID XO MGN XGN PB MD PDFC KNLG. \"FGNBA ZBFG KH XGE. WE WHO WHF KNLG ZBFG LGHL QGHN ZE KN ZNBM RD\", LE MHA ID.\n" +
                "\n" +
                "XGEFC LE FEN WE AHFC ZBFG PB UDB FGDFC LH CNH KNFG HFG KE RDHF XGHFG, 31 MDEN, MJD QGDEFC PNFG WEL Z, UDHF ZNFG LGHFG, MQ GLA LDFC XGEFC MGB PB PN XGEFC MGB ZE JEN 7 LEFC FGHF MJEFC RDEFC AHO KD KH MHA KEFC AHO GEF 4 MGHFC.\n" +
                "\n" +
                "XGEFC LEF MGD FGHQ FGDFC PHF QGHN MJH WDEFC LGE LEFC FGHF, MNBF MGDB FGH PH HF DEFC, EFC LGD RDEFC AHO FGHA MNFG AEN MGHFC AHM GEF 15 MJNBD KEFC. MNBD IHLG MNBF MNBM XNBA, GHN MGHFC FHO HFG XGHFG XGEFC KD XGH FHFC KEFC MNBF FGH. FCDEN PE QGHN KHFC MNF RNF WDEFC MGDL LGE 11 FCDEN.\n" +
                "\n" +
                "\"GHN FHA AE RDEFC, ANFG LGDH MDFC FCGN QGHN KN RNF GE MJE KB IEFC UDH FCHO\", HFG XGHFG FEN. MNFG KEFC RDEFC, LGE LEFC FGHF FCGN, FGDFC FCGN LHFG LGHM PHM MNA MGE WHFG FCGB XGN AE RDEFC, HFG WHN MGEN PH LE CHFC CND LGHF LEFC FGHF.\n" +
                "\n" +
                "\"FGDFC WHE KEFC MGHE PNBL, FGNBD XNFG FCGNBA ZE PB UDB LE MGB CHO JH MNFG MJHFC KDM CHO FCDEF WHE KEFC. AHM MGEN CNHF JHM KHN KB QGDL GEN IHF RDHM\", EFC WB UDHFC MJDFC - FCDOBF QGE LDL MJDEFC QGD MJHLG LDL PNBL WHA, ZE WHE KEFC - MGDEFC ZNFG PH RH GEN FEN.\n" +
                "\n" +
                "AEM WO KE UDHF MJEFC XGHL XGNBF PE LGEFC LGN MGDO E ZNFG KDEFC, HFG GDEFC PH CNH KNFG HFG XGHFG E MQ GLA UDOBM KNFG XGEFC PB UDB WH \"PB LDFC LGHFC ZNBM WHA CN JH MNBF\" MJEFC XGN LE GEN QGDL GEN LHE GEF XGN GE E WHN. \"XGN MGHFG QGE AE WHN, FCDEN WHE KEFC NM FGHM KDEL KN WHA MHN LEFC MO LD. LDFC PEN KE WH LE GEN MNA XNBA LEFC PNBL AEN XGN QGDL GEN XNFG MB. MGD FGHQ MDFC ZDEL KDEL LHN MGNBF\", EFC MJDFC KD ZHE.\n" +
                "\n" +
                "KNBD FHO KH RHO JH PEN HFG GDEFC. LGE WHA MJDEL KNLG KH CNDL LE UDHO WHN. AEN FCHO LE KDEL MJH 100.000 KEFC LGE 4 KBF 4,5 MNBFC ZHF GHFC, FCHFC PEN ADL WDEFC LD.\n" +
                "\n" +
                "LGN MGDO WHA PNBL MJEFC RN FCGNBQ FCGNBQ AHO MGEN PD GEN MGHFC 4/2020. LEFC PNBL CNDQ LGN LE ADL MGD FGHQ EF KNFG GEF FCGB QGD GE.  HFG: FGHF PHM LDFC LHQ.\n" +
                "LGN MGDO WHA PNBL MJEFC RN FCGNBQ AHO E ZNFG KDEFC, KHD MGHFC 10/2021. HFG: FGHF PHM LDFC LHQ.\n" +
                "\n" +
                "FGNBD GEHM KEFC KDEL FEN WEFC, MGDO ZHF PEN LGEFC RNF KN WHA MGEN PD MHN LHL RN FCGNBQ AHO. LEFC PNBL AHFC WHN ADL MGD FGHQ EF KNFG, XGEFC QGHN QGD MGDEL PHE MGEN MNBM FGD QGD GE. FEN IE XGEFC LE FEN MDOBF LDH MGDO MGHFG MGDH XGN AEM RN FCGNBQ AHO CEN LE KN WHA, IHD PHN FCHO FEQ KEF. LGEFC LE LDFC FGHF MNF LGDHF ZN KN RHO MJEFC MGHFC MEN. \"E WHN WHA AEN LE MNBF. MBM PB UDB LGDH ADEF\", HFG KEFC PNBF PE, \"LEF CNE, LE KEN LDFC XGEFC PB\".\n" +
                "\n" +
                "ZHE LHE MGN MJDEFC WHE KEFC KE FHPNCEI PDH LEFC ZE LGE ZNBM, 56,7% KEHFG FCGNBQ MGHA CNH XGHE IHM MDOBF KDFC FCHO WHQ MDL IHD XGN UDHO MJE WHN GEHM KEFC ZNFG MGDEFC. MJEFC KE, 50% IB MDOBF FGHF PNBF GEHF MEHF AEN. MJDFC MHA KD ZHE FGD LHD FGHF WDL PH MGEFC MNF MGN MJDEFC WHE KEFC MQ GLA (SHWAN) LDFC LGE ZNBM MGHFG QGE LHF MDOBF KDFC XGEHFC 44.000-57.000 WHE KEFC MJEFC UDO NP.\n" +
                "\n" +
                "GHN FCHO MJDEL, RDEFC AHO LDH HFG XGHFG KH FGHF KDEL KEF GHFC KHD MNBF IHD CHF FDH FHA, WHA PHN MJHA LGNBL XGHD MJHFC MGBD GEH LDH XGHLG UDBF. XGEFC MGHA PHE KHD IE PEN IE KEF GHFC HFG FGHF MJDEL KNLG, FGDFC FGD LHD MJEFC AHO AHL KHF LGDOBF ZNBF. EFC LGD RDEFC AHO ZHM KHD WNBF WHL PEN LHL KHD AEN LDFC LHQ PHN, FCDOBF WNBD.\n" +
                "\n" +
                "\"XGN MGHFG QGE KHF AE LDH MJE, HM LE FGDFC LE GEN LGE FGDFC FCDEN E WHN\", EFC LGD KE RDHF XGHFG FEN.";

        System.out.println(printMap(thongKeTanSoMoiKyTu(input, initAlphabet()),30));
        System.out.println(printMap(thongKeTanSoMoiKyTu(input, initDsPhuAmKep()),10));
        //System.out.println(printMap(thongKeTanSoMoiKyTu(input,LIST_VAN_3_KY_TU)));



    }
}





