'''
Created on 30.08.2011

@author: kca
'''

from logging.handlers import BufferingHandler

class BufferingHandler(BufferingHandler):
	def __init__(self, capacity = None, *args, **kw):
		super(BufferingHandler, self).__init__(capacity = capacity, *args, **kw)
		
	def shouldFlush(self, record):
		return self.capacity and super(BufferingHandler, self).shouldFlush(record) or False
