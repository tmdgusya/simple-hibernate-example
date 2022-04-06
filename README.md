# Roach 의 Hibernate 간단 구조

## 목적

Hibernate 의 코드를 실제로 까보는 사람도 많지 않을뿐더러, 그 안의 원리구조를 이해하는 것 또한 힘들다고 생각합니다.  
그래서 가볍게 읽기 좋은 방식으로 Hibernate 내부의 Event-Driven 구조 그리고 DirtyChecking 등에 대해서 적어보려고 합니다.  
간략하게 생략하는 부분이 많다보니 추상적인 부분은 비슷하나, 디테일한 내용은 다를수 있습니다. 이해바랍니다.

## Event-Driven

기본적으로 Persist 가 발생하면 PersistEvent 가 발생합니다.  
PersistEvent 는 EntityId, Entity 정보를 담고 있고 이미 영속화된 객체일 경우 Dirty-Checking 을 진행합니다.  
Dirty-Checking 간 변경점 발생시 실제 물리적 저장소에 UPDATE Query 가 실행됩니다.

## 배울 수 있는 부분

- Hibernate 는 Entity 의 특정 변경, 영속 등의 Event 를 발행하여 관리함을 알 수 있습니다.
- Dirty-Checking 이 Entity 의 변경점을 변경하는 것을 알 수 있습니다.
    - 변경 여부만 확인하고 그대로 UPDATE QUERY 를 만드므로 특정 변경점만 UPDATE QUERY 로 나가지 않음을 알 수 있습니다.(? 이건 사실 내 코드로는 알 수 있을까 싶기도..)

## 아직 구현안된 부분

- Session
    - 어떻게 구현할지 고민중.. 그냥 Config Class 로 둘까도 고민중이다.
    - Session.MANUAL_MODE => Dirty-Checking 안하는 부분 구현. 사실 session.flush() 도 구현해야 되는데 흐음.

## 구현 안할 부분

- 실제 물리적 저장소에 붙을 마음은 없음. 그러면 일이 너무 커짐, 진짜 ORM 을 만들어야 함.


