# Programmers.Level4_Java_CardGame

## 프로그래머스 Dynamic Programming > 카드게임

### 1. 문제설명
문제: https://programmers.co.kr/learn/courses/30/lessons/42896

input으로 int형 배열 left와 right가 온다. 두 배열은 카드더미를 의미하며 일정할 룰에 따라 카드를 버리고 점수를 얻는다.

>1. 언제든지 왼쪽 카드만 통에 버릴 수도 있고 왼쪽 카드와 오른쪽 카드를 둘 다 통에 버릴 수도 있다. 이때 얻는 점수는 없다.
>2. 오른쪽 카드에 적힌 수가 왼쪽 카드에 적힌 수보다 작은 경우에는 오른쪽 카드만 통에 버릴 수도 있다. 오른쪽 카드만 버리는 경우에는 오른쪽 카드에 적힌 수만큼 점수를 얻는다.
>3. (1)과 (2)의 규칙에 따라 게임을 진행하다가 어느 쪽 더미든 남은 카드가 없다면 게임이 끝나며 그때까지 얻은 점수의 합이 최종 점수가 된다.

위의 규칙에 따라 게임을 진행할때 얻을 수 있는 점수의 최대값 return하는 문제

### 2. 풀이
2차원 배열을 만들어 해당 위치까지 다다랏을 때의 최대 점수을 값으로 담는다.

```map[0][0] = 0```으로 시작하며 (1)번 룰에 따라 
```java
map[i+1][j] = map[i][j]; map[i+1][j+1] = map[i][j];
```
(2)번 룰에 따라
```java
if (left[i] > right[j])
  map[i][j+1] = map[i][j] + right[j];
```
로 적용할 수있다.
위 과정을 반복하여 가장 마지막 행과 열에 다다르면 카드더미를 모두 사용하여 게임이 종료됫을 때의 점수 값이 들어있으므로 그중 최대값을 찾아 return하여 해결해주었다.

### 3. 어려웠던 점
```java
computeScore(int leftIdx, int rightIdx, score);
...
computeScore(i, j, score) = Math.max(computeScore(i+1, j, score), computeScore(i+1, j+1, score), computeScore(i, j+1, score + right[j]));
```
와 같이 Divide & Conquer (분할정복)으로 해결하려 하였으나 중복된 요소를 다시 계산하는 과정으로 인해 input size가 클수록 지수적으로 시간이 늘어난다. 겹치는 계산이 있는것으로 배열공간을 만들어 DP를 적용해야겠다고 생각하게 되었다.

역방향으로 map[len][len]부터 시작하여 map[0][0]까지 올라오는 코드도 있었는데 훨씬 간결하고 DP수업때 들었던 <b>DNA 서열정렬</b>과 유사하여 보였다. 겹치는것이 있다면 DP를 떠올려 설계할 수 있도록 연습해야겠다.
