#!/usr/bin/python
from typing import List

from User import *


class Post:
    def __init__(self, id: int, message: str, user: User, wall: User, publishingDate: str, likedBy: List[User]):
        """ erzeugt ein Post-Objekt, das über Methoden die Informationen zurückgibt """
        self._id = id
        self._message = message
        self._user = user
        self._wall = wall
        self._publishingDate = publishingDate
        self._likedBy = likedBy

    @property
    def id(self) -> int:
        return self._id

    @property
    def message(self) -> str:
        return self._message

    @property
    def user(self) -> User:
        return self._user

    @property
    def userId(self) -> id:
        return self._user.id

    @property
    def username(self) -> str:
        return self._user.username

    @property
    def wall(self) -> User:
        return self._wall

    @property
    def publishingDate(self) -> str:
        return self._publishingDate

    @property
    def likedBy(self) -> List[User]:
        return self._likedBy
