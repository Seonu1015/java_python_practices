<img src="https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=header&text=miniProject&fontSize=60&fontAlign=77"/>

### 간단한 RPG 게임 구현

___

프로그래밍에 익숙해지기 위해 RPG 게임을 간단하게 구현해 보았습니다.
교육 과정을 통해 배운 것을 바탕으로 java로 먼저 작성하였고,
python으로 바꿔보는 작업을 진행했습니다.

---

### _Java_

#### Unit (캐릭터, 몬스터, 보스)

- 최소 공격력과 최대 공격력을 설정하여 랜덤한 공격력을 반영
- 캐릭터는 태생에 따라 스탯을 다르게 설정

#### Battle

- 5층마다 보스몬스터를 배치해서 총 20층까지 전투를 진행
- 보스층을 클리어하고 나면 일반 몬스터들의 체력과 공격력이 증가
- 보스전은 3번째 턴마다 스킬을 시전하고 주사위게임을 응용해서 캐릭터가 회피할 수 있게 처리

#### Item

- 일반 몬스터와 보스 몬스터는 처치시 물약을 1~3개 랜덤하게 드랍
- 보스 몬스터는 보스 무기 드랍

### _Python_

#### 변경 사항
1. 클래스와의 관련성을 확보하기 위해 흩어져있던 메서드들의 위치 변경
2. 분리하여 작성 가능한 메서드는 작은 작업단위로 분리
3. 예외처리 추가

#### Unit

- Monster와 Boss를 csv를 불러오는 방식으로 변경

#### Battle

- 한 층에서 일반 몬스터와 10번 전투 후 보스 전투가 진행으로 변경

#### Item

- 물약을 일반 포션과 특별 포션으로 분리
- 특별 포션의 경우 전체 체력을 회복하지만 확률적으로 중독을 발생

---

#### 미니 프로젝트를 진행하며 느낀점

