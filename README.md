# SpringBoot_EnjoyTrip
Converting EnjoyTrip Project to Spring Boot Project


### 개요
웹 백엔드 개발 운영 시 사용자 정보를 저장하고 효율적으로 메모리를 관리할 수 있는 솔루션으로 Java EE에서는 Session을 제공하고 있다. 하지만 개발 편의성과 유지보수의 편의성 증진을 위하여 백엔드 서버와 프론트엔드 서버를 분리함으로서 유효한 JSessionId를 주고받는 기존의 Session 시스템을 유지할 수 없는 문제점이 발생하였다. 이에 새로운 Session을 정의하고 RequestBody를 통해서 주고받을 수 있는 Session Id를 발급하여 문제를 해결하고자 하였다.


### 소감
HttpSession의 자동 세션 종료를 구현해야 했는데 NodeJS의 비동기 처리를 이용하면 쉽게 만들 수 있을 것 같아서 두 서버를 사용하도록 구현했는데 다 완성하고 나니 미처 아쉬움이 남는다.
몰두해서 구현할 때는 자바의 Quartz패키지를 미처 찾아보지 않았고 원래 계획은 grpc를 이용해서 두 서버의 통신을 구현하려고 했는데 시간이 없어서 그것까진 차마 하지 못했다. Interface나 상속을 더 활용해볼수는 없었을까. 보안을 고려해본다면 Https를 사용했어야 했다.
그래도 세션 한 번 만들어보면서 내부 로직 좀 고민해보고 이전에는 해볼 생각도 안하던 것을 시도해봤다는 것에 큰 의의가 있다고 본다.

### Session sequence diagram

#### Login
![Login-sequence.png](readme_img%2FLogin-sequence.png)

#### Logout
![Logout-sequence.png](readme_img%2FLogout-sequence.png)

#### Node Session Manager
![NodeSessionManager.png](readme_img%2FNodeSessionManager.png)


## 개발 환경
Front-end: Vue.js(VSCode)
Back-end: Java(STS), Node.js(VSCode)
DataBase: MySQL