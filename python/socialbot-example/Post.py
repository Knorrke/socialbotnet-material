#!/usr/bin/python
from typing import List

from User import *


class Post:
    def __init__(self, id: int, message: str, publishingDate: str, user: User, wall: User, trendingScore: float, likes: int, likedBy: List[User]):
        """ erzeugt ein Post-Objekt, das über Methoden die Informationen zurückgibt """
        self._id = id
        self._message = message
        self._publishingDate = publishingDate
        self._user = user
        self._wall = wall
        self._trendingScore = trendingScore
        self._likes = likes
        self._likedBy = likedBy

    @property
    def id(self) -> int:
        return self._id

    @property
    def message(self) -> str:
        return self._message

    @property
    def publishingDate(self) -> str:
        return self._publishingDate

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
    def trendingScore(self) -> float:
        return self._trendingScore

    @property
    def likes(self) -> int:
        return self._likes

    @property
    def likedBy(self) -> List[User]:
        return self._likedBy
