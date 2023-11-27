# SpringBoot_EnjoyTrip
Converting EnjoyTrip Project to Spring Boot Project




### 개요
웹 백엔드 개발 운영 시 사용자 정보를 저장하고 효율적으로 메모리를 관리할 수 있는 솔루션으로 Java EE에서는 Session을 제공하고 있다. 하지만 개발 편의성과 유지보수의 편의성 증진을 위하여 백엔드 서버와 프론트엔드 서버를 분리함으로서 유효한 JSessionId를 주고받는 기존의 Session 시스템을 유지할 수 없는 문제점이 발생하였다. 이에 새로운 Session을 정의하고 RequestBody를 통해서 주고받을 수 있는 Session Id를 발급하여 문제를 해결하고자 하였다.