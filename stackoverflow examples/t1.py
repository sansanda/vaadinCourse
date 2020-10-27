'''
Created on 28 oct. 2019

@author: gh
'''


def val(l):
    return l[0][3]
 
compara =  {(40, 900, 40, 73.5355750487331),
 (40, 1000, 20, 90.4995126705654),
 (40, 1000, 40, 85.0097465886941),
 (45, 700, 20, 76.4424951267057),
 (45, 700, 40, 74.2909356725147),
 (45, 800, 20, 80.2022417153996),
 (45, 800, 40, 74.9195906432748),
 (45, 900, 20, 83.0116959064327),
 (45, 900, 40, 78.3333333333333),
 (45, 1000, 20, 87.8898635477583),
 (45, 1000, 40, 86.1135477582845),
 (50, 700, 20, 77.7802144249512),
 (50, 700, 40, 70.0730994152048),
 (50, 800, 20, 82.1003898635477),
 (50, 800, 40, 79.7587719298247),
 (50, 900, 20, 83.396686159844),
 (50, 900, 40, 80.0487329434699),
 (50, 1000, 20, 83.6281676413255),
 (50, 1000, 40, 83.3284600389864)}

l = list(compara)
#print(l[3][4])

l.sort(key=val, reverse=False)

import time 

import calendar 

import datetime

mifecha=time.strptime(fechaUltimaCalibracion,"%Y-%m-%d")

print (fechaUltimaCalibracion) #imprime correctamente la fecha según se ve

print(mifecha)

prox=mifecha+datetime.timedelta(days=365) # esto da error print (prox) proximLabel=Label(campos, text="Próxima calibración en: "+str(prox)+" días")

