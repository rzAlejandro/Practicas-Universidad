// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>



// función que resuelve el problema
int resolver(std::vector<int> datos,int L) {
	int numParches = 1;
	int iniAct = datos[0];
	for (int i = 1; i < datos.size(); ++i) {
		if (datos[i] - iniAct > L) {
			iniAct = datos[i];
			numParches++;
		}
	}
	return numParches;
}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int N, L, num;
	std::cin >> N >> L;
	if (!std::cin)
		return false;
	std::vector<int> datos;
	for (int i = 0; i < N; ++i) {
		std::cin >> num;
		datos.push_back(num);
	}
	if (N == 0)
		std::cout << 0 << '\n';
	else {
		int sol = resolver(datos, L);
		std::cout << sol << '\n';
	}
	

	// escribir sol


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