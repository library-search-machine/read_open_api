# read_open_api
검색엔진을 만들기 위해 오픈 api를 읽어들이는 방법을 모색합니다

<br>
<br>

# 💾 데이터
```
도서관 정보나루는 전국의 도서관 데이터를 오픈 API로 제공해주고 있다.
우리는 지역을 서울로 한정하여 도서관 325개관의 책 25,399,865권의 데이터를 사용하였다.
```
[도서관 정보나루 홈페이지](https://www.data4library.kr/)
<br>

## 데이터 예시
|번호|도서명|저자|출판사|발행년도|ISBN|권|주제분류번호|도서관이름|full-text
|------|---|---|------|---|---|------|---|---|------|---|---|------|
|1|맨큐의경제학|맨큐|Cengage LEarning|2015|9788962183672||9||320|1|1|2022-08-11|
|2|어부와 아내|김서정|시공사|2007|9788952748218||||388.1|1|1|2022-07-18|


<br>
<br>
<br>

## 🤔 트러블 슈팅
<details>
    <summary>
        <b>오픈API를 사용하여 데이터를 가져오는데 시간이 많이 소요된다</b>
    </summary>
<br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>원인:</b> 오픈 API를 사용하여 데이터를 가져와 저장할려고 했지만 1000건의 데이터당 10초 60,000건의 데이터에는 1시간이 소유되었다. 
  <br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>해결방안:</b> 오픈 API 대신 도서관 325개관의 csv파일을 전부 다운받아 DB에 저장하였다.

</details>
