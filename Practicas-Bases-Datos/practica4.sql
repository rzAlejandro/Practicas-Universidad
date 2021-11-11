@'C:\hlocal\crear_tablas.sql';

SET SERVEROUTPUT ON SIZE 1000000;

CREATE OR REPLACE PROCEDURE pr_empleados_tlf
(p_tel�fono TEL�FONOS.Tel�fono%TYPE)
IS
    v_nombre EMPLEADOS.Nombre%TYPE;
    v_dni EMPLEADOS.Dni%TYPE;
BEGIN
    SELECT Nombre, Dni INTO v_nombre, v_dni
    FROM EMPLEADOS natural join TEL�FONOS
    WHERE TEL�FONOS.Tel�fono=p_tel�fono;
     
    DBMS_OUTPUT.put_line('El empleado con el tel�fono ' || p_tel�fono || ' es: ' || v_nombre || ', con DNI: ' || v_dni || '.');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.put_line('No se encontr� el empleado con el tel�fono ' || p_tel�fono || '.');
    WHEN TOO_MANY_ROWS THEN
        DBMS_OUTPUT.put_line('Hay m�s de un empleado con el tel�fono ' || p_tel�fono || '.');
END;
/

CREATE OR REPLACE PROCEDURE pr_comprobar_poblaciones
IS
    CURSOR c_numProv IS
        SELECT C1.Poblaci�n
        FROM "C�digos postales"  C1, "C�digos postales"  C2
        WHERE C1.Poblaci�n=C2.Poblaci�n and C1.Provincia<>C2.Provincia;
        
    v_nombre "C�digos postales".Poblaci�n%TYPE;

BEGIN
    OPEN c_numProv;
    
    FETCH c_numProv INTO v_nombre;
    IF c_numProv%NOTFOUND THEN
        DBMS_OUTPUT.put_line('No hay dos o m�s provincias que compartan la misma poblaci�n.');
    ELSE 
        DBMS_OUTPUT.put_line('A la poblaci�n ' || v_nombre || ' no le corresponde siempre la misma provincia.');
    END IF;
    
    CLOSE c_numProv; 
END;
/


CREATE OR REPLACE PROCEDURE pr_empleados_CP
IS
    CURSOR c_emp IS
        SELECT DOMICILIOS."C�digo postal", EMPLEADOS.Nombre, DOMICILIOS.Calle, EMPLEADOS.Sueldo
        FROM EMPLEADOS INNER join DOMICILIOS
        on DOMICILIOS.DNI=EMPLEADOS.DNI
        ORDER BY DOMICILIOS."C�digo postal";
        
    CURSOR c_emp2 IS
        SELECT DOMICILIOS."C�digo postal", COUNT(EMPLEADOS.Dni), AVG(EMPLEADOS.Sueldo)
        FROM EMPLEADOS INNER join DOMICILIOS
        on DOMICILIOS.DNI=EMPLEADOS.DNI
        GROUP BY DOMICILIOS."C�digo postal"
        ORDER BY DOMICILIOS."C�digo postal";
        
    v_c�digoP DOMICILIOS."C�digo postal"%TYPE;
    v_c�digoP2 DOMICILIOS."C�digo postal"%TYPE;
    v_nombre EMPLEADOS.Nombre%TYPE;
    v_calle DOMICILIOS.Calle%TYPE;
    v_sueldo EMPLEADOS.Sueldo%TYPE;
    v_numEmp NUMBER(3);
    v_totEmp NUMBER(3) := 0;
    v_mediaSueldo EMPLEADOS.Sueldo%TYPE;
    
BEGIN
    OPEN c_emp;
    OPEN c_emp2;
    LOOP
        FETCH c_emp2 INTO v_c�digoP, v_numEmp, v_mediaSueldo;
        EXIT WHEN c_emp2%NOTFOUND;
        DBMS_OUTPUT.put_line('C�digo postal: ' || v_c�digoP);
        
        FOR i IN 0..v_numEmp-1
        LOOP
            FETCH c_emp INTO v_c�digoP2, v_nombre, v_calle, v_sueldo;
                DBMS_OUTPUT.put_line('  ' || v_nombre || ', ' || v_calle || ', ' || v_sueldo);
        END LOOP;
        
        v_totEmp := v_totEmp + v_numEmp;
        DBMS_OUTPUT.put_line('      N� empleados: ' || v_numEmp || ', Sueldo medio: ' || v_mediaSueldo);
    END LOOP;
    
    DBMS_OUTPUT.put_line('Total empleados: ' || v_totEmp);
    
    CLOSE c_emp;
    CLOSE c_emp2; 
END;
/


BEGIN
--vista1
    pr_empleados_tlf('666666666');
    pr_empleados_tlf('611111111');
    pr_empleados_tlf('913333333');
    DBMS_OUTPUT.put_line('');
    
--vista2
    pr_comprobar_poblaciones();
    INSERT INTO "C�digos postales" VALUES ('41008','Arganda','Sevilla');
    pr_comprobar_poblaciones();
    DELETE FROM "C�digos postales" WHERE 
    "C�digos postales"."C�digo postal" = '41008' and 
    "C�digos postales".Poblaci�n = 'Arganda' and 
    "C�digos postales".Provincia = 'Sevilla';
    DBMS_OUTPUT.put_line('');
    
--vista3
    pr_empleados_CP();    
END;
/