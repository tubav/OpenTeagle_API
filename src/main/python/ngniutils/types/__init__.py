'''
Created on 01.09.2011

@author: kca
'''

from ..logging import LoggerMixin
from types import ModuleType
import sys
from ngniutils.collections import get_iterable
from ngniutils import NOT_SET

class ImmutableType(type):
	def __call__(self, *args, **kw):
		if args and isinstance(args[0], self):
			return args[0]
		return super(ImmutableType, self).__call__(*args, **kw)

class TypeManagerType(LoggerMixin, type):
	def __init__(self, *args, **kw):
		super(TypeManagerType, self).__init__(*args, **kw)
		modname = self.__module__ + "." + self.__name__
		if self.__module__ != __name__:
			self.logger.debug("Registering %s as %s", self, modname)
			sys.modules[modname] = self
			self.__module_name__ = modname


class AbstractTypeManager(LoggerMixin, ModuleType):	
	__metaclass__ = TypeManagerType
	
	def __init__(self, name = None, *args, **kw):
		name = name or str(id(name))
		self.modulename = self.__module_name__ + "." + getattr(self, "__prefix__", self.__class__.__name__) + name
		self.logger.debug("Registering %s as %s", self, self.modulename)
		sys.modules[self.modulename] = self

	def create_type(self, name, base = (), dict = {}, metaclass = type):
		try:
			existing = getattr(self, name)
			if not isinstance(existing, type):
				raise ValueError(name)
		except AttributeError:
			pass
		
		base = get_iterable(base)
		self.logger.debug("Creating %s %s(%s) with %s", metaclass.__name__, name, base, dict) 
		dict["__module__"] = self.modulename
		type = metaclass(name, base, dict)
		setattr(self, name, type)
		return type
