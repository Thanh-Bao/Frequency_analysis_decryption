import java.sql.Array;
import java.util.*;

public class Ma_Hoa_Thay_The {

    public static final int TOTAL_LETTERS_ALPHABET = 26;
    public static final int ASCII_CODE_OF_A = 65;
    public static final int ASCII_CODE_OF_Z = 90;

    private static char randomLetter() {
        Random random = new Random();
        return (char) (random.nextInt(ASCII_CODE_OF_Z + 1 - ASCII_CODE_OF_A) + ASCII_CODE_OF_A);
    }

    public static String printKey(Map<Character, Character> keyPair) {
        StringBuilder key = new StringBuilder();
        for (char i : keyPair.values()) {
            key.append(i);
        }
        return key.toString();
    }

    public static Map<Character, Character> keyPairRandomGenerator() {
        Map<Character, Character> table = new HashMap<Character, Character>();
        ArrayList<Character> tempList = new ArrayList<Character>(); //chứa những số đã random ra rồi
        for (int i = ASCII_CODE_OF_A; i < ASCII_CODE_OF_A + TOTAL_LETTERS_ALPHABET; i++) {
            char value = randomLetter();
            while (tempList.contains(value)) { // random cho đến khi có giá trị không trùng
                value = randomLetter();
            }
            table.put((char) i, value);
            tempList.add(value);
        }
        return table;
    }

    public static String replaceEncrypt(String input, Map<Character, Character> keyPair) {
        StringBuilder result = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (keyPair.containsKey(ch)) { // chỉ mã hóa ký tự in hoa A->Z
                result.append(keyPair.get(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String decrypt(String input, Map<Character, Character> keyPair) {
        StringBuilder result = new StringBuilder();
        // đảo ngược keyPair
        Map<Character, Character> tempMap = new HashMap<Character, Character>();
        for (char ch : keyPair.keySet()) {
            tempMap.put(keyPair.get(ch), ch);
        }
        // chỉ giải mã ký tự in hoa A->Z
        for (char ch : input.toCharArray()) {
            if (tempMap.containsKey(ch)) {
                result.append(tempMap.get(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void main(String args[]) {
        Map<Character, Character> keyPair = keyPairRandomGenerator();
        String input = "\"Về hay ở?\", Nguyễn Thu Thuỷ quay sang hỏi chồng khi cả xóm trọ đã về hết. Cuối cùng họ quyết định ở lại để tìm cơ hội thay đổi công việc.\n" +
                "\n" +
                "Năm năm đi làm ăn xa, đây là lần đầu Thuỷ lưỡng lự. Vợ chồng Thuỷ làm phụ hồ. Anh Nguyễn Thanh Có, 32 tuổi, chồng Thuỷ được trả 300.000 đồng một ngày công. Nữ giới giá bèo hơn, chỉ 250.000 đồng một ngày. \"Nắng mới có việc, chứ mưa thì chẳng có đồng nào\", Thuỷ kể về đặc thù công việc.\n" +
                "\n" +
                "Covid-19 bùng phát khiến hai vợ chồng thất nghiệp từ đầu tháng 6. Đã ba tháng nay họ đã xin khất khoản tiền trọ 1,5 triệu đồng mỗi tháng. Gia đình bốn người trông chờ vào nguồn lương thực được phát từ thiện và khoản hỗ trợ của chính quyền.\n" +
                "\n" +
                "Căn phòng trọ chưa đầy 10 m2 của vợ chồng chị Thuỷ và hai con ở trong suốt 4 tháng dịch. Ảnh: Nhân vật cung cấp.\n" +
                "Căn phòng trọ chưa đầy 10 m2 của vợ chồng chị Thuỷ và hai con tại Bình Dương hôm 8/10. Ảnh: Nhân vật cung cấp.\n" +
                "\n" +
                "Thấy người từ các tỉnh đổ về, mẹ Thuỷ liên tục gọi điện thoại giục con. Trong 7 ngày, Hậu Giang quê cô đã đón hơn 9.000 người về quê. Nhưng cạn tiền, sợ mang bệnh về quê, vợ chồng cô cố bám trụ. \"Dù ở lại cũng chẳng an toàn lại đói hơn ở quê\", cô thở dài. Hơn một tháng trước, chồng Thủy là F0 sau một lần ra ngoài.\n" +
                "\n" +
                "Người lớn có bữa cơm, bữa cháo ăn kèm cá khô, mắm ruốc, ngán lại đổi sang mì gói. \"Nhưng bọn trẻ cần sữa\", Thủy nói. Không thể vay mượn tiền, cô tìm các nhóm hỗ trợ xin sữa cho con. Ngại nhưng vào đường cùng, cô đành cầu cứu trên mạng. Ba lần đi xin, hai con nhỏ 4 tuổi và 2 tuổi được nhận 350.000 đồng, cùng bánh sữa của nhà hảo tâm. Chiều 7/10 con lại đòi sữa. Số sữa cũ hết cách đây vài tuần, Thuỷ để lại thông tin kèm số điện thoại dưới bài kêu gọi. Cả tối, cô chầu trực điện thoại, mong có người liên lạc.\n" +
                "\n" +
                "Nhìn xóm trọ vắng ngắt ở thị xã Tân Uyên, tỉnh Bình Dương, vợ chồng Thủy biết mình là số ít những người không chịu hòa mình vào dòng người đổ về các tỉnh miền Tây hoặc ra Bắc những ngày qua. Theo thống kê của Bộ Công an, kể từ khi các tỉnh phía Nam hết giãn cách từ 1/10, có khoảng 2,1 triệu trong tổng số 3,5 triệu lao động tại TP HCM, Bình Dương, Đồng Nai và Long An muốn về quê.\n" +
                "\n" +
                "Hàng nghìn người dân chờ ở chốt kiểm soát trên quốc lộ 1A, huyện Bình Chánh TP HCM để chờ về quê ở các tỉnh miền Tây, ngày 1/10. Ảnh: Quỳnh Trần.\n" +
                "Hàng nghìn người dân chờ ở chốt kiểm soát trên quốc lộ 1A, huyện Bình Chánh TP HCM để chờ về quê ở các tỉnh miền Tây, ngày 1/10. Ảnh: Quỳnh Trần.\n" +
                "\n" +
                "Sống trong căn trọ chật hẹp suốt bốn tháng, nhưng gia đình Trần Ánh Hương, 22 tuổi, trú tại Hóc Môn không định về Bạc Liêu tránh dịch. Dễ đi, khó trở lại là nỗi lo chung của Hương và nhiều lao động. \"Mình có thể bị mất việc nếu không kịp quay lại khi công ty gọi đi làm\", cô nói, \"Chưa kể người ở quê sợ dân Sài Gòn về lắm. Lỡ lại là F0\".\n" +
                "\n" +
                "Lo lắng của Hương không phải vô căn cứ. Đến ngày 5/10, Cần Thơ ghi nhận 30 trong 54 ca mắc Covid-19 mới là người ở tỉnh khác về; Hậu Giang có 19 ca, Trà Vinh 10 ca, Bến Tre 8 trong ca; Đồng Tháp có 60 trong số 67 ca đều là người hồi hương.\n" +
                "\n" +
                "Khu cách ly quá tải, một số tỉnh cho người về quê theo dõi sức khoẻ tại nhà, nếu xét nghiệm âm tính hoặc đã tiêm vaccine. Đỡ tốn kém, nhưng Hương e ngại sự kỳ thị khi về từ vùng dịch. \"Nhiễm bệnh đã khổ. Lỡ lây lan dịch bệnh chắc phải bỏ đi biệt xứ\", cô tâm sự.\n" +
                "\n" +
                "Không có nỗi lo mang bệnh về quê nhưng cả gia đình anh Đỗ Xuân Khanh, 31 tuổi, trú phường Vĩnh Lộc B, quận Bình Chánh, TP HCM cũng không thể về vì không thể bỏ rơi 7 công nhân trong xưởng may dù đã tạm đóng máy hơn 4 tháng.\n" +
                "\n" +
                "Không còn thu nhập nhưng vẫn phải trả lương cho công nhân, tiền thuê nhà và ăn uống, ông chủ xưởng may nhẩm tính mỗi tháng mất hơn 15 triệu đồng. Tiêu sạch tiền tiết kiệm, hai tháng nay anh Khanh không đủ khả năng đóng tiền nhà. Người vợ phải đăng tin xin lương thực cho 11 người.\n" +
                "\n" +
                "\"Hai năm mở xưởng, mình chưa từng nghĩ phải đi xin hỗ trợ để sống qua ngày\", anh Khanh nói. Tính đóng xưởng, cho công nhân nghỉ, nhưng nghĩ cảnh chật vật tìm thợ lành nghề khi mở xưởng, anh lại thôi và cố gắng giữ chân công nhân.\n" +
                "\n" +
                "\"Những lao động thạo việc, nhiều kinh nghiệm bỏ về quê có thể gây ra tình trạng đứt gãy nguồn lao động. Mất thời gian rất dài để phục hồi sản xuất\", ông Lê Quang Trung - Nguyên Phó Cục trưởng phụ trách Cục việc làm, Bộ Lao động - Thương binh và Xã hội nói.\n" +
                "\n" +
                "Một lý do quan trọng khác khiến vợ chồng chị Thủy ở Bình Dương, Ánh Hương và gia đình anh Khanh ở TP HCM quyết định không về quê là \"về cũng chẳng biết làm gì ra tiền\" trong khi cơ hội phục hồi cao hơn khi họ ở lại. \"Khi thành phố mở lại, người lao động ít nhất được đi làm tại công ty cũ. Cùng với đó là cơ hội tìm kiếm công việc mới khi phục hồi kinh tế. Thu nhập từng bước được cải thiện\", ông Trung dự báo.\n" +
                "\n" +
                "Điều này đã xảy ra với Ánh Hương. Chỗ làm trước dịch đã giục cô quay lại. Mỗi ngày cô được trả 100.000 đồng cho 4 đến 4,5 tiếng bán hàng, ngang với mức lương cũ.\n" +
                "\n" +
                "Chị Thuỷ làm việc trong xí nghiệp nghiệp may thời vụ hồi tháng 4/2020. Công việc giúp chị có mức thu nhập ổn định hơn nghề phụ hồ.  Ảnh: Nhân vật cung cấp.\n" +
                "Chị Thuỷ làm việc trong xí nghiệp may ở Bình Dương, đầu tháng 10/2021. Ảnh: Nhân vật cung cấp.\n" +
                "\n" +
                "Nhiều hoạt động được nới lỏng, Thuỷ bàn với chồng xin đi làm thời vụ tại các xí nghiệp may. Công việc mang lại mức thu nhập ổn định, không phải phụ thuộc vào thời tiết như phụ hồ. Nỗi sợ không có nơi tuyển của Thuỷ thành thừa khi một xí nghiệp may gọi cô đi làm, sau vài ngày nộp đơn. Chồng cô cũng nhận tin chuẩn bị đi xây trong tháng tới. \"Ở lại làm mới có tiền. Tết về quê chưa muộn\", anh động viên vợ, \"Còn giờ, có đói cũng không về\".\n" +
                "\n" +
                "Báo cáo thị trường lao động do Navigos vừa công bố cho biết, 56,7% doanh nghiệp tham gia khảo sát tuyển dụng ngay lập tức sau khi quay trở lại hoạt động bình thường. Trong đó, 50% sẽ tuyển nhân viên hoàn toàn mới. Trung tâm Dự báo nhu cầu nhân lực và Thông tin thị trường lao động TP HCM (Falmi) cũng cho biết thành phố cần tuyển dụng khoảng 44.000-57.000 lao động trong quý IV.\n" +
                "\n" +
                "Hai ngày trước, xưởng may của anh Khanh đã nhận được đơn hàng đầu tiên sau gần nửa năm, làm vài trăm chiếc khẩu trang thêu hoa của khách quen. Không thấm vào đâu so với số đơn hàng anh nhận trước dịch, nhưng nhu cầu trong may mặc dần chuyển biến. Ông chủ xưởng may bắt đầu liên lạc với các đầu mối cung cấp vải, nguyên liệu.\n" +
                "\n" +
                "\"Khi thành phố dần mở cửa trở, ắt có những cơ hội cho những người ở lại\", ông chủ Đỗ Xuân Khanh nói.";
        input = Thong_Ke_Percent.xoaDauAndUppercase(input);
        System.out.println("Random key: " + printKey(keyPair));
        System.out.println("*******************************************************************************************************");
        String output = replaceEncrypt(input, keyPair);
        System.out.println(output);

    }
}
