Nikita Nesterenko:
1) Использовать только пост / гет

2) вернуть реквест парам вместо патс варибл
3)
			@Override
				public void deleteUser(User user) {
				user = entityManager.merge(user);
				entityManager.remove(user);
                } зачем тут мерж?

4) не писать логику в контроллере
----------
Konstantin Harin:

По пункту 3. ИМХО, из слоя DAO разумно отдавать отсоединенные (detached) объекты.
Значит, объекты на удаление могут быть как "detached", так и "managed".
Согласно схеме https://i.stack.imgur.com/Hez6p.png нельзя перейти из состояния "detached" в "removed" минуя "managed".
Таким образом с методом merge код более устойчивый.