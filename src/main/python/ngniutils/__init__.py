from .logging import LoggerMixin
	
Base = LoggerMixin
	
class NotSet(object):
	__slots__ = ()
	
	def __bool__(self):
		return False
	__nonzero__ = __bool__
	
	def __str__(self):
		return ""
	
NOT_SET = NotSet()

DEFAULT_ENCODING = "utf-8"
DEFAULT_CHUNK_SIZE= 4 * 1024 * 1024
BASE_STR = unicode

def noop(*args, **kw):
	pass

def not_implemented(*args, **kw):
	raise NotImplementedError()

def uc(s):
	if isinstance(s, unicode):
		return s
	if isinstance(s, basestring):
		return s.decode(DEFAULT_ENCODING)
	return unicode(s)

def encstr(s):
	if isinstance(s, str):
		return s
	if not isinstance(s, unicode):
		s = unicode(s)
	return s.encode(DEFAULT_ENCODING)
	
def tostr(o):
	if isinstance(o, basestring):
		return o
	return BASE_STR(o)

def identity(x):
	return x

_isc = issubclass
def issubclass(o, classes):
	return isinstance(o, type) and _isc(o, classes)