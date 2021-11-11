// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <algorithm>
//#include "queue_eda.h"
#include "bintree_eda.h"


int mod(int x){
	if (x < 0)return -x;
	return x;
}
template <typename T>
int altura(bintree<T> arbol) {
	if (arbol.empty())
		return 0;
	else {
		return std::max(altura(arbol.right()), altura(arbol.left())) + 1;
	}
}
template <typename T>
bintree<T> desequilIzq(bintree<T> left, T elem, bintree<T> right) {
	int hii = altura(left.left());
	int hid = altura(left.right());
	if (hii >= hid) {
		return { left.left(),left.root(),{left.right(),elem,right} };
	}
	else
		return { {left.left(),left.root(),left.right().left()},left.right().root() ,{left.right().right(),elem,right} };
}

template <typename T>
bintree<T> desequilDer(bintree<T> left, T elem, bintree<T> right) {
	int hdd = altura(right.right());
	int hdi = altura(right.left());
	if (hdd >= hdi) {
		return { {left,elem,right.left()}, right.root(),right.right()};
	}
	else
		return { {left,elem,right.left().left()},right.left().root() ,{right.left().right(),right.root(),right.right()} };
}



template <typename T>
bintree<T> equilibrar(bintree<T> left,T root,bintree<T> right) {
	int hi = altura(left);
	int hd = altura(right);
	if (mod(hi - hd) <= 1)
		return { left,root,right };
	else {
		if (hi == hd + 2) 
			return desequilIzq(left,root,right);
		else return desequilDer(left, root, right);
	}
}


template <typename T>
bintree<T> insertar(bintree<T> arbol, T elem) {
	if (arbol.empty())
		return { elem};
	//return arbol.bintree(elem)
	else {
		if (elem < arbol.root()){
			return equilibrar(insertar(arbol.left(), elem), arbol.root(), arbol.right());
		}
		if (elem > arbol.root()){
			return equilibrar(arbol.left(), arbol.root(), insertar(arbol.right(), elem));
		}
	}

}

// función que resuelve el problema
void resolver(bintree<int> arbol,int k1,int k2) {
	if(arbol.empty()){}
	else {
		if (arbol.root() <= k1) {
			if (arbol.root() == k1) {
				std::cout << arbol.root()<<' ';
			}
			resolver(arbol.right(), k1, k2);
		}
		else {	//Ya tenemos que el elemento es mayor que el mínimo.
		
			if (arbol.root() <= k2) {//El elemento pertenece al intervalo.
				resolver(arbol.left(), k1, k2);
				std::cout << arbol.root() << ' ';
				if (arbol.root() < k2)
					resolver(arbol.right(), k1, k2);
			}
			else {//El elemento es mayor que el intervalo
				resolver(arbol.left(), k1, k2);
			}
		}
	
				
	}
}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int N, num, k1, k2;
	std::cin >> N;
	if (N==0)
		return false;
	bintree<int> arbol;
	//std::vector<int> sol;
	for (int i = 0; i < N; ++i) {
		std::cin >> num;
		arbol=insertar(arbol, num);
	}
	std::cin >> k1 >> k2;

	// escribir sol
	resolver(arbol, k1, k2);
	std::cout << '\n';
	return true;

}

int main() {
	// Para la entrada por fichero.
	// Comentar para acepta el reto
#ifndef DOMJUDGE
 std::ifstream in("datos.txt");
 auto cinbuf = std::cin.rdbuf(in.rdbuf()); //save old buf and redirect std::cin to casos.txt
#endif 


	while (resuelveCaso())
		;


	// Para restablecer entrada. Comentar para acepta el reto
#ifndef DOMJUDGE // para dejar todo como estaba al principio
std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif

	return 0;
}