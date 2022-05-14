#!/usr/bin/python

from tkinter import *

from Socialbot import *


class GUI:
    def __init__(self):
        self._sb = None
        self._gui = Tk()
        self._varwin = None
        gui = self._gui
        gui.title("Socialbot")
        self._create_label = Label(gui, text="Bot anmelden")
        self._create_label.pack()
        methoddata = Socialbot.__init__.__code__
        variables = methoddata.co_varnames[:methoddata.co_argcount][1:]
        self._labels, self._entries = [], []
        for v in variables:
            self._labels.append(Label(gui, text=v))
            self._labels[-1].pack()
            self._entries.append(Entry(gui, bd=3))
            self._entries[-1].pack()
        self._create = Button(gui, text="Erstellen", width=50,
                              command=lambda: self._createBot(self._entries[0].get(), self._entries[1].get()))
        self._create.pack()
        gui.mainloop()

    def _createBot(self, user, pwd):
        if user == "" or pwd == "":
            print("Felder dürfen nicht leer bleiben.")
        else:
            self._sb = Socialbot(user, pwd)
            self._create_label.pack_forget()
            self._create.pack_forget()
            for l in self._labels:
                l.pack_forget()
            for e in self._entries:
                e.pack_forget()
            methods = dir(Socialbot)
            self._actions = {}
            for method in methods:
                if not method.startswith("_"):
                    button = Button(self._gui, text=method, width=50, command=lambda method=method: self._startWindow(method))
                    methoddata = eval("Socialbot.%s.__code__" % method)
                    self._actions[method] = (button, methoddata.co_varnames[:methoddata.co_argcount][1:])
                    button.pack()

    def _startWindow(self, method):
        if len(self._actions[method][1]) > 0:
            if self._varwin == None:
                self._varwin = Toplevel(self._gui)
                self._varwin.protocol("WM_DELETE_WINDOW", lambda: self._closeWindow())
                labels, entries = [], []
                for v in self._actions[method][1]:
                    labels.append(Label(self._varwin, text=v))
                    labels[-1].pack()
                    entries.append(Entry(self._varwin, bd=3))
                    entries[-1].pack()
                execute = Button(self._varwin, text="Ausführen", width=50, command=lambda method=method: self._methodForSB(
                    "%s(%s)" % (method, ",".join(['"%s"' % x.get() for x in entries]))))
                execute.pack()
        else:
            self._methodForSB(method + "()")

    def _closeWindow(self):
        if self._varwin != None:
            self._varwin.destroy()
            self._varwin = None

    def _methodForSB(self, action):
        sb = self._sb
        eval("sb." + action)
        self._closeWindow()


gui = GUI()
