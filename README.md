# Sherpa
An app that moves travelers' luggage on their behalf
### 🔨 사용 기술 스택
* Spring boot : 2.7.4
* MySql : 8.0

해당 프로젝트의 서버는 AWS RDS를 데이터베이스 서버로 이용하였으며 JAR 빌드를 통해 AWS Elastic Beanstalk를 이용하여 서버를 백엔드 서버를 배포하였습니다.

### 📜 Sherpa 백엔드 API 명세서

#### 1. Member API by MemberConroller😊
<img width="500" alt="image" src="https://user-images.githubusercontent.com/70957918/208093679-1ac2f12e-9add-4109-9017-2110ed7df2d1.png">
* 회원가입 : 회원가입 API
* 로그인 : 로그인 API 

#### 2. Order API by OrderController 🚗
<img width="561" alt="image" src="https://user-images.githubusercontent.com/70957918/208093618-b488b654-60f3-402f-a504-3dee0d482a02.png">
* Luggage Size : Luggage Format Rule

* Order 생성 : Order 생성 API
* Order 수락 : 여행객이 등록한 Order를 배송자가 수락하는 API
* Order 변경 : 여행객이 등록한 Order를 수정하는 API
* Order 완료 : 배송자가 Order 배송을 완료하는 API
* Order 취소 : 여행객이 등록한 Order를 취소 또는 배송자가 수락한 Order를 취소하는 API
* Order findyByMemberId : Member의 ID로 Order를 검색하여 호출하는 API
* Order findById : Order의 Order ID로 Order를 검색하여 호출하는 API
* Order findByDistance : 입력받은 배송자의 위치에 대해 적절한 배송 리스트를 호출해주는 API
* Order 삭제 : Order를 삭제하는 API

#### 3. Report API by ReportController ⛔
<img width="544" alt="image" src="https://user-images.githubusercontent.com/70957918/208093771-95b27927-e75b-4a4c-bf74-3639a3a635b1.png">
* Report Create : Report를 생성하는 API
* Report findByReportId : Report ID로 Report를 검색하여 호출하는 API
* Report findByReporterId : Member의 ID로 신고를 한 Report를 검색하여 호출하는 API
* Report findByReportedId : Member의 ID롤 신고를 당한 Report를 검색하여 호출하는 API

#### 4. Cafe API by CafeController ☕
<img width="539" alt="image" src="https://user-images.githubusercontent.com/70957918/208094136-534cf5f6-ca6d-45b9-aa02-171143008a5e.png">
* Cafe findById : cafe의 ID로 Cafe를 검색하여 호출하는 API
* Cafe findByDistance : 인근의 터미널일 존재하는 경우를 위해 해당 주소 1km 내에 있는 Cafe를 검색하여 호출하는 API
