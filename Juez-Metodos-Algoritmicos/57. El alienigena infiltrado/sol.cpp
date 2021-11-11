// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include <algorithm>


// función que resuelve el problema
bool myfunction(std::pair<int, int> p1, std::pair<int, int> p2) {
	return p1.first < p2.first;
}


// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int C, F, N, ci, fi;
	std::cin >> C >> F >> N;
	if (C==0 && F==0 && N==0)
		return false;
	std::vector<std::pair<int, int>> v1;
	for (int i = 0; i < N; ++i) {
		std::cin >> ci >> fi;
		v1.push_back({ ci,fi });
	}
	std::sort(v1.begin(), v1.end(), myfunction);


	int act = C;
	int sig = C;
	int sol = 1;
	int i = 0;
	bool desc = false, ok = true;
	while (i < N && !desc && ok) {
		std::pair<int,int> interv = v1[i];
		if (interv.first > act) {
			if (interv.first > sig)
				ok = false;
			act = sig;
			sol++;
		}
		sig = std::max(sig, interv.second);
		if (sig >= F)
			desc = true;
		++i;
	}
	if (desc && ok)
		std::cout << sol << '\n';
	else std::cout << "Imposible\n";
	
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