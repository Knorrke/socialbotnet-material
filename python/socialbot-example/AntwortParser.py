#!/usr/bin/python

import json
from User import *
from Post import *

class AntwortParser:
	def __init__(self):
		pass
	def zuUserArray(self, antwort):
		'''
		Versucht die Antwort in ein User-Array zu parsen.
		antwort: Antwort des Servers
		return: User-Objekte im Array
		'''
		return self._jsonZuUserArray(json.loads(antwort))
	def zuPostArray(self, antwort):
		'''
		Versucht die Antwort in ein Post-Array zu parsen.
		antwort: Antwort des Servers
		return: Post-Objekte im Array
		'''
		return self._jsonZuPostArray(json.loads(antwort))

	def _jsonZuUserArray(self, array):
		users = []
		for obj in array:
			print(obj)
			users.append(self._zuUser(obj))
		return users
	def _zuUser(self, obj):
		return User(obj["id"], obj["username"], obj["hobbies"], obj["about"])
	def _jsonZuPostArray(self, array):
		posts = []
		for obj in array:
			pid = obj["id"]
			message = obj["message"]
			publishingDate = obj["publishingDate"]
			user = self._zuUser(obj["user"])
			wall = self._zuUser(obj["wall"])
			trendingScore = obj["trendingScore"]
			likes = obj["likes"]
			likedBy = self._jsonZuUserArray(obj["likedBy"])
			posts.append(Post(pid, message, publishingDate, user, wall, trendingScore, likes, likedBy))
		return posts