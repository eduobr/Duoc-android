CREATE TABLE mascota(
    codigo NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    raza VARCHAR2(100) NOT NULL,
    edad NUMBER NOT NULL
);

CREATE SEQUENCE SQ_MASCOTA
START WITH 1
INCREMENT BY 1;

--procedimiento agregar
CREATE OR REPLACE PROCEDURE SP_AGREGA_MASCOTA(p_nombre VARCHAR2,
                                              p_raza VARCHAR2, p_edad NUMBER) IS
BEGIN
    INSERT INTO mascota VALUES(SQ_MASCOTA.NEXTVAL, p_nombre, p_raza, p_edad);
END SP_AGREGA_MASCOTA;

--procedimiento de borrar
CREATE OR REPLACE PROCEDURE SP_BORRAR_MASCOTA(p_codigo NUMBER) IS
BEGIN
    DELETE FROM mascota WHERE codigo=p_codigo;
END SP_BORRAR_MASCOTA;

--procedimiento buscar
CREATE OR REPLACE PROCEDURE SP_BUSCAR_MASCOTA(p_codigo NUMBER, p_nombre OUT VARCHAR2,
                                              p_raza OUT VARCHAR2, p_edad OUT NUMBER) IS
BEGIN

    SELECT nombre, raza, edad
    INTO p_nombre, p_raza, p_edad
    FROM mascota
    WHERE codigo=p_codigo;

END SP_BUSCAR_MASCOTA;

--PROCEDIMIENTO LISTAR -REGALO PARA EL LISTAR DE LOS DATOS
CREATE OR REPLACE PROCEDURE 
        SP_LISTAR_MASCOTA(p_cursor OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN p_cursor FOR
    SELECT codigo, nombre, raza, edad
    FROM mascota;
    
END SP_LISTAR_MASCOTA;