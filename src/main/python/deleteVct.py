'''
Created on Jun 11, 2012

@author: gca
'''
import sys
from teagle.repository.tssg.TSSGRepository import TSSGRepository
from teagle.repository.entities_default import Vct


    
def main(argv):                          
    repoUrl = "http://root:r00t@localhost:9000/repository/rest"                
    for arg in argv: 
	print arg
    vctName = argv[1]
    print "The vct name is %s"%vctName
    repo = TSSGRepository(repoUrl)
        
    
    vct = repo.get_unique_entity(Vct, commonName = vctName)
    vct.providesResources = []
    repo.persist(vct)
    repo.delete_entity(vct)



if __name__ == "__main__":
    main(sys.argv)
