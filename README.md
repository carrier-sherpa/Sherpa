# Sherpa

### ๐จ ์ฌ์ฉ ๊ธฐ์  ์คํ
* Spring boot : 2.7.4
* MySql : 8.0

ํด๋น ํ๋ก์ ํธ์ ์๋ฒ๋ AWS RDS๋ฅผ ๋ฐ์ดํฐ๋ฒ ์ด์ค ์๋ฒ๋ก ์ด์ฉํ์์ผ๋ฉฐ JAR ๋น๋๋ฅผ ํตํด AWS Elastic Beanstalk๋ฅผ ์ด์ฉํ์ฌ ๋ฐฑ์๋ ์๋ฒ๋ฅผ ๋ฐฐํฌํ์์ต๋๋ค.

*** 
### ๐ Sherpa ๋ฐฑ์๋ API ๋ช์ธ์

#### 1. Member API by MemberConroller๐
<img width="500" alt="image" src="https://user-images.githubusercontent.com/70957918/208093679-1ac2f12e-9add-4109-9017-2110ed7df2d1.png">

* ํ์๊ฐ์ : ํ์๊ฐ์ API
* ๋ก๊ทธ์ธ : ๋ก๊ทธ์ธ API 

#### 2. Order API by OrderController ๐
<img width="561" alt="image" src="https://user-images.githubusercontent.com/70957918/208093618-b488b654-60f3-402f-a504-3dee0d482a02.png">

* Luggage Size : Luggage Format Rule

* Order ์์ฑ : Order ์์ฑ API
* Order ์๋ฝ : ์ฌํ๊ฐ์ด ๋ฑ๋กํ Order๋ฅผ ๋ฐฐ์ก์๊ฐ ์๋ฝํ๋ API
* Order ๋ณ๊ฒฝ : ์ฌํ๊ฐ์ด ๋ฑ๋กํ Order๋ฅผ ์์ ํ๋ API
* Order ์๋ฃ : ๋ฐฐ์ก์๊ฐ Order ๋ฐฐ์ก์ ์๋ฃํ๋ API
* Order ์ทจ์ : ์ฌํ๊ฐ์ด ๋ฑ๋กํ Order๋ฅผ ์ทจ์ ๋๋ ๋ฐฐ์ก์๊ฐ ์๋ฝํ Order๋ฅผ ์ทจ์ํ๋ API
* Order findyByMemberId : Member์ ID๋ก Order๋ฅผ ๊ฒ์ํ์ฌ ํธ์ถํ๋ API
* Order findById : Order์ Order ID๋ก Order๋ฅผ ๊ฒ์ํ์ฌ ํธ์ถํ๋ API
* Order findByDistance : ์๋ ฅ๋ฐ์ ๋ฐฐ์ก์์ ์์น์ ๋ํด ์ ์ ํ ๋ฐฐ์ก ๋ฆฌ์คํธ๋ฅผ ํธ์ถํด์ฃผ๋ API
* Order ์ญ์  : Order๋ฅผ ์ญ์ ํ๋ API

#### 3. Report API by ReportController โ
<img width="544" alt="image" src="https://user-images.githubusercontent.com/70957918/208093771-95b27927-e75b-4a4c-bf74-3639a3a635b1.png">

* Report Create : Report๋ฅผ ์์ฑํ๋ API
* Report findByReportId : Report ID๋ก Report๋ฅผ ๊ฒ์ํ์ฌ ํธ์ถํ๋ API
* Report findByReporterId : Member์ ID๋ก ์ ๊ณ ๋ฅผ ํ Report๋ฅผ ๊ฒ์ํ์ฌ ํธ์ถํ๋ API
* Report findByReportedId : Member์ ID๋กค ์ ๊ณ ๋ฅผ ๋นํ Report๋ฅผ ๊ฒ์ํ์ฌ ํธ์ถํ๋ API

#### 4. Cafe API by CafeController โ
<img width="539" alt="image" src="https://user-images.githubusercontent.com/70957918/208094136-534cf5f6-ca6d-45b9-aa02-171143008a5e.png">

* Cafe findById : cafe์ ID๋ก Cafe๋ฅผ ๊ฒ์ํ์ฌ ํธ์ถํ๋ API
* Cafe findByDistance : ์ธ๊ทผ์ ํฐ๋ฏธ๋์ผ ์กด์ฌํ๋ ๊ฒฝ์ฐ๋ฅผ ์ํด ํด๋น ์ฃผ์ 1km ๋ด์ ์๋ Cafe๋ฅผ ๊ฒ์ํ์ฌ ํธ์ถํ๋ API
