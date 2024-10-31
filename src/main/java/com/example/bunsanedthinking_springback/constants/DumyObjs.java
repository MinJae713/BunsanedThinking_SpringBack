package com.example.bunsanedthinking_springback.constants;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.entity.employee.Sales;
import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyType;
import com.example.bunsanedthinking_springback.entity.product.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DumyObjs {
	public static final Customer[] DUMY_CUSTOMERS = {
			new Customer("카즈하", "010-1112-1112", "대학생", 27, Gender.Female ,"111111-1111111", "서대문구", 10000L, "신한", "1012-215-225937"),
			new Customer("장원영", "010-2223-2223", "유튜버", 23, Gender.Female, "222222-2222222", "송파구", 40000L, "우리", "1012-215-225937"),
			new Customer("권은비", "010-3334-3334", "물총장수", 25, Gender.Female, "333333-3333333", "마포구", 30000L, "국민", "1012-215-225937"),
			new Customer("이강인", "010-4445-4445", "축구선수", 23, Gender.Male, "444444-4444444", "종로구", 45000L, "하나", "1012-215-225937"),
			new Customer("오소름", "010-5556-5556", "영업사원", 42, Gender.Male, "555555-5555555", "강남구", 50000L, "우리", "1012-215-225937"),
			new Customer("최그램", "010-6667-6667", "파티플래너", 56, Gender.Male, "666666-6666666", "상록구", 25000L, "국민", "1012-215-225937")
	};
	public static final Department[] DUMY_DEPARTMENTS = {
			new Department("융자운용팀", "융자운용", "대출 상품 관리, 대출 원리금 수금, 대출금 요청", "김대현"),
			new Department("계약관리팀", "계약관리", "분납/수금 처리, 만기 계약 심사, 배서 심사, 부활 심사, 제지급금 요청", "김찬"),
			new Department("보상기획팀", "보상기획", "협력업체 평가, 협력업체 관리", "허리나"),
			new Department("U/W팀", "인수", "공동인수,재보험 신청, 인수 심사", "모지환"),
			new Department("상품관리팀", "상품관리", "보험 상품 관리", "박보검"),
			new Department("영업팀", "영업", "영업 성과 평가, 보험 상담 처리, 보험 상품 영업, 대출 상품 영업", "김민재"),
			new Department("고객정보관리팀", "고객정보관리", "고객정보관리", "손흥민"),
			new Department("인사관리팀", "인사관리", "추가 수당 요청, 복리후생비용 요청, 직원 정보 관리", "이상혁"),
			new Department("경영기획팀", "경영기획", "부서 정보 관리", "김수현"),
			new Department("고객지원팀", "고객지원", "신고 처리, 민원 처리", "김채원"),
			new Department("보상처리팀", "보상처리", "보상 요청", "안유진"),
			new Department("총무관리팀", "총무관리", "집기비품 재고 관리", "최예나"),
			new Department("재무회계팀", "재무회계", "세금 납부 내역 조회, 지급 사항 처리, 입금 사항 조회", "박지성")
	};
	public static final OfficeSupply[] DUMY_OFFICESUPPLY = {
			new OfficeSupply("모나미 볼펜", "문구", 370),
			new OfficeSupply("지우개", "문구", 400),
			new OfficeSupply("A4", "문구", 20000),
			new OfficeSupply("B5", "문구", 5000),
			new OfficeSupply("스테이플러", "문구", 20),
			new OfficeSupply("가위", "문구", 70),
			new OfficeSupply("칼", "문구", 20),
			new OfficeSupply("에어컨", "전자제품", 37),
			new OfficeSupply("선풍기", "전자제품", 760),
			new OfficeSupply("컴퓨터", "전자제품", 3000),
			new OfficeSupply("모니터", "전자제품", 3000)
	};
	public static final Employee[] DUMY_EMPLOYEES = {
			new Employee("박지성", EmployeePosition.GeneralManager, "송파구", "010-6543-6543", "우리",
					"1012-215-225937", "654321-987543", 9101, 504, dateGetter("2013-10-23")),
			new Employee("최예나", EmployeePosition.GeneralManager, "종로구", "010-7654-7654", "하나",
					"532-215-362224", "765432-198754", 9102, 454, dateGetter("2005-07-12")),
			new Employee("안유진", EmployeePosition.GeneralManager, "중구", "010-8765-8765", "우리", "101-215-234526",
					"876543-219765", 9103, 484, dateGetter("2011-12-03")),
			new Employee("다나카", EmployeePosition.GeneralManager, "성동구", "010-9876-9876", "국민",
					"532-215-124522", "987654-329876", 9104, 631, dateGetter("2007-03-13")),
			new Employee("김수현", EmployeePosition.GeneralManager, "강동구", "010-9123-9123", "신한",
					"654-215-352223", "912345-678923", 9105, 584, dateGetter("2019-01-02")),
			new Employee("이상혁", EmployeePosition.GeneralManager, "강남구", "010-8912-8912", "하나",
					"129-215-234632", "891234-567812", 9106, 399, dateGetter("2015-07-17")),
			new Employee("손흥민", EmployeePosition.GeneralManager, "강남구", "010-7891-7891", "우리",
					"162-215-263357", "789123-456891", 9107, 476, dateGetter("2009-04-28")),
			new Sales("김민재", EmployeePosition.GeneralManager, "마포구", "010-6789-6789", "국민", "132-215-464637",
					"678912-345679", 9108, 436, dateGetter("2010-10-13")),
			new Employee("박보검", EmployeePosition.GeneralManager, "덕양구", "010-5678-5678", "신한",
					"762-215-644632", "567891-234678", 9109, 590, dateGetter("2016-05-27")),
			new Employee("모지환", EmployeePosition.GeneralManager, "송파구", "010-4567-4567", "우리",
					"612-215-823858", "456789-543567", 9110, 602, dateGetter("2012-12-15")),
			new Employee("허리나", EmployeePosition.GeneralManager, "성북구", "010-3456-3456", "하나",
					"162-215-325692", "945678-912256", 9111, 455, dateGetter("2015-10-28")),
			new Employee("김찬", EmployeePosition.GeneralManager, "상록구", "010-2345-2345", "국민",
					"752-215-634663", "734567-891255", 9112, 456, dateGetter("2012-07-19")),
			new Employee("김대현", EmployeePosition.GeneralManager, "서대문구", "010-8434-1394", "신한",
					"432-215-659937", "872346-789185", 9113, 560, dateGetter("2013-12-26")),
			new Employee("김계란", EmployeePosition.SeniorStaff, "송파구", "010-8388-7264", "우리",
					"1029-281-285729", "981121-2175629", 9114, 320, dateGetter("2020-09-03")),
			new Employee("나만두", EmployeePosition.Intern, "종로구", "010-7284-8264", "하나", "1590-752-558212",
					"990121-0215822", 9115, 200, dateGetter("2024-12-26")),
			new Employee("감자탕", EmployeePosition.Manager, "중구", "010-2632-6442", "우리", "4732-192-219851",
					"880519-2715842", 9116, 380, dateGetter("2018-09-11")),
			new Employee("웅치킨", EmployeePosition.Staff, "성동구", "010-1846-7719", "국민", "1102-128-129835",
					"820812-2957381", 9117, 260, dateGetter("2022-12-26")),
			new Employee("최피자", EmployeePosition.DeputyGeneralManager, "강동구", "010-6428-8074", "신한",
					"1892-291-397538", "740728-5729221", 9118, 440, dateGetter("2017-02-04")),
			new Employee("정양파", EmployeePosition.Intern, "강남구", "010-9942-4712", "하나", "5820-575-271251",
					"690412-1950223", 9119, 200, dateGetter("2024-12-26")),
			new Employee("이족발", EmployeePosition.Manager, "강남구", "010-1249-3741", "우리",
					"8827-805-485416", "740627-0285721", 9120, 380, dateGetter("2018-03-07")),
			new Sales("김맥북", EmployeePosition.SeniorStaff, "마포구", "010-0029-4756", "국민", "3693-993-593886",
					"921107-0285218", 9121, 320, dateGetter("2020-10-08")),
			new Employee("바선생", EmployeePosition.SeniorStaff, "덕양구", "010-9472-4489", "신한",
					"3692-181-482749", "851210-1028512", 9122, 320, dateGetter("2020-04-03")),
			new Employee("코그모", EmployeePosition.Staff, "송파구", "010-3491-7492", "우리", "1240-922-920284",
					"801027-1057296", 9123, 260, dateGetter("2022-12-26")),
			new Employee("나진심", EmployeePosition.DeputyGeneralManager, "성북구", "010-2145-9625", "하나",
					"1259-420-998204", "731227-1058288", 9124, 440, dateGetter("2017-11-02")),
			new Employee("인심이", EmployeePosition.Intern, "상록구", "010-2159-0812", "국민", "2994-140-229450",
					"910515-2856311", 9125, 200, dateGetter("2024-05-17")),
			new Employee("나졸려", EmployeePosition.Staff, "서대문구", "010-2956-7724", "신한", "2873-239-971275",
					"850817-1057298", 9126, 260, dateGetter("2022-06-19"))
	};
	private static final Date dateGetter(String dateStr) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static final PartnerCompany[] DUMY_PARTNER_COMPANIES = {
			new PartnerCompany("저스티스리그", "010-2583-6914", PartnerCompanyType.RoadsideAssistanceCompany, "슈퍼맨", "010-7258-3691"),
			new PartnerCompany("어벤져스", "010-4725-8369", PartnerCompanyType.DamageAssessmentCompany, "아이언맨", "010-1472-5836")
	};
	public static final Product[] DUMY_PRODUCTS = {
			new Disease(InsuranceType.Disease, "뇌전증대비", 3000, 40, "뇌전증을대비하는보험입니다", 35, 24, "뇌전증", 2, 3),
			new Disease(InsuranceType.Disease, "심장마비대비", 4000, 30, "심장마비를대비하는보험입니다", 45, 30, "심장마비", 2, 2),
			new Injury(InsuranceType.Injury, "골절대비", 300, 20, "골절을대비하는보험입니다", 5, 10, InjuryType.Minor, 3),
			new Injury(InsuranceType.Injury, "눈찔림대비", 500, 50, "눈찔림사고를대비하는보험입니다", 2, 10, InjuryType.Minor, 3),
			new Automobile(InsuranceType.Automobile, "악사", 5000, 40, "인명피해를대비하는보험입니다", 9, 7, 7,
					VehicleType.Medium, getDumyServiceType())
	};
	private static final ArrayList<ServiceType> getDumyServiceType() {
		ArrayList<ServiceType> serviceType = new ArrayList<>();
		serviceType.add(ServiceType.EmergencyTowing);
		return serviceType;
	}
}
