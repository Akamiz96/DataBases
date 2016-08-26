/*
Proyecto de Bases de datos 2016-30

Realizado por:
  Pablo Ariza
  Alejandro Castro
  Andres Contreras
  Santiago Salamanca
*/

/* Creacion de tablas (No olvidar DROP TABLE)*/
DROP TABLE tasas;
DROP TABLE monedaXpais;
DROP TABLE moneda;
DROP TABLE pais;
DROP TABLE continente;

CREATE TABLE continente(
  cod_continente numeric (3,0) PRIMARY KEY,
  descripcion varchar (50) NOT NULL UNIQUE
);
CREATE TABLE pais (
  cod_continente numeric (3,0),
  cod_pais numeric (3,0),
  nombre varchar (30) NOT NULL UNIQUE,
  PRIMARY KEY (cod_continente,cod_pais),
  FOREIGN KEY (cod_continente) REFERENCES continente(cod_continente)
);
CREATE TABLE moneda(
  id_moneda numeric (3,0) PRIMARY KEY,
  nombre varchar (40) NOT NULL UNIQUE
);
CREATE TABLE monedaXpais(
  id_moneda numeric (3,0),
  cod_continente numeric (3,0),
  id_pais numeric (3,0),
  PRIMARY KEY (id_moneda,cod_continente,id_pais),
  FOREIGN KEY (id_moneda) REFERENCES moneda(id_moneda),
  FOREIGN KEY (cod_continente,id_pais) REFERENCES pais(cod_continente,cod_pais)
);
CREATE TABLE tasas(
  id_moneda_origen numeric (3,0),
  id_moneda_destino numeric (3,0),
  fecha date,
  tasa_cambio numeric (20,10),
  PRIMARY KEY (id_moneda_origen,id_moneda_destino,fecha),
  FOREIGN KEY (id_moneda_origen) REFERENCES moneda(id_moneda),
  FOREIGN KEY (id_moneda_destino) REFERENCES moneda(id_moneda),
  check (tasa_cambio > 0)
);

/* Insercion de datos en las tablas */

/*CONTINENTES*/
INSERT INTO continente values (1,'Europa');
INSERT INTO continente values (2,'America');
INSERT INTO continente values (3,'Oceania');
INSERT INTO continente values (4, 'Asia');
INSERT INTO continente values (5,'Africa');

/*PAISES EUROPA*/
INSERT INTO pais values (1,1,'Inglaterra');
INSERT INTO pais values (1,2,'Alemania');
INSERT INTO pais values (1,3,'Portugal');
INSERT INTO pais values (1,4,'Italia');
INSERT INTO pais values (1,5,'Espa�a');
INSERT INTO pais values (1,6,'Suecia');
INSERT INTO pais values (1,7,'Suiza');
INSERT INTO pais values (1,8,'Monaco');
INSERT INTO pais values (1,9,'Francia');
INSERT INTO pais values (1,10,'Belgica');

/*PAISES AMERICA*/
INSERT INTO pais values (2,1,'Estados Unidos');
INSERT INTO pais values (2,2,'Canada');
INSERT INTO pais values (2,3,'Mexico');
INSERT INTO pais values (2,4,'Honduras');
INSERT INTO pais values (2,5,'Jamaica');
INSERT INTO pais values (2,6,'Panama');
INSERT INTO pais values (2,7,'Colombia');
INSERT INTO pais values (2,8,'Venezuela');
INSERT INTO pais values (2,9,'Chile');
INSERT INTO pais values (2,10,'Brasil');

/*PAISES OCEANIA*/
INSERT INTO pais values (3,1,'Australia');
INSERT INTO pais values (3,2,'Nueva Zelanda');
INSERT INTO pais values (3,3,'Islas Marshall');
INSERT INTO pais values (3,4,'Vanuatu');
INSERT INTO pais values (3,5,'Papua Nueva Guinea');
INSERT INTO pais values (3,6,'Kiribati');
INSERT INTO pais values (3,7,'Samoa');
INSERT INTO pais values (3,8,'Nauru');
INSERT INTO pais values (3,9,'Tonga');
INSERT INTO pais values (3,10,'Islas Salomon');

/*PAISES ASIA*/
INSERT INTO pais values (4,1,'China');
INSERT INTO pais values (4,2,'Filipinas');
INSERT INTO pais values (4,3,'Corea del Sur');
INSERT INTO pais values (4,4,'Singapur');
INSERT INTO pais values (4,5,'Laos');
INSERT INTO pais values (4,6,'Malasia');
INSERT INTO pais values (4,7,'Tailandia');
INSERT INTO pais values (4,8,'Japon');
INSERT INTO pais values (4,9,'Camboya');
INSERT INTO pais values (4,10,'India');

/*PAISES AFRICA*/
INSERT INTO pais values (5,1,'Madagascar');
INSERT INTO pais values (5,2,'Ghana');
INSERT INTO pais values (5,3,'Egipto');
INSERT INTO pais values (5,4,'Nigeria');
INSERT INTO pais values (5,5,'Etiopia');
INSERT INTO pais values (5,6,'Kenia');
INSERT INTO pais values (5,7,'Mali');
INSERT INTO pais values (5,8,'Marruecos');
INSERT INTO pais values (5,9,'Mozambique');
INSERT INTO pais values (5,10,'Tunez');

/*Insercion de monedas de los paises*/
INSERT INTO moneda values (1,'Euro');
INSERT INTO moneda values (2,'Libra Esterlina');
INSERT INTO moneda values (3,'Corona Sueca');
INSERT INTO moneda values (4,'Franco Suizo');
INSERT INTO moneda values (5,'Dolar USA');
INSERT INTO moneda values (6,'Dolar Canadiense');
INSERT INTO moneda values (7,'Peso Mexicano');
INSERT INTO moneda values (8,'Lempira');
INSERT INTO moneda values (9,'Dolar Jamaiquino');
INSERT INTO moneda values (10,'Balboa');
INSERT INTO moneda values (11,'Peso Colombiano');
INSERT INTO moneda values (12,'Bolivar');
INSERT INTO moneda values (13,'Peso Chileno');
INSERT INTO moneda values (14,'Real Brasile�o');
INSERT INTO moneda values (15,'Dolar Australiano');
INSERT INTO moneda values (16,'Dolar Neozelandes');
INSERT INTO moneda values (17,'Vatu');
INSERT INTO moneda values (18,'Kina');
INSERT INTO moneda values (19,'Dolar de Kiribati');
INSERT INTO moneda values (20,'Tala');
INSERT INTO moneda values (21,'Pa?anga');
INSERT INTO moneda values (22,'Dolar de las Islas Salomon');
INSERT INTO moneda values (23,'Yuan Renminbi');
INSERT INTO moneda values (24,'Peso Filipino');
INSERT INTO moneda values (25,'Won');
INSERT INTO moneda values (26,'Dolar de Singapur');
INSERT INTO moneda values (27,'Kip');
INSERT INTO moneda values (28,'Ringgit Malaysio');
INSERT INTO moneda values (29,'Baht');
INSERT INTO moneda values (30,'Yen');
INSERT INTO moneda values (31,'Riel');
INSERT INTO moneda values (32,'Rupia Hindu');
INSERT INTO moneda values (33,'Ariary Malgache');
INSERT INTO moneda values (34,'Cedi');
INSERT INTO moneda values (35,'Libra Egipcia');
INSERT INTO moneda values (36,'Naira');
INSERT INTO moneda values (37,'Birr Etiope');
INSERT INTO moneda values (38,'Chelin Keniata');
INSERT INTO moneda values (39,'Franco CFA Occidental');
INSERT INTO moneda values (40,'Dirham Marroqui');
INSERT INTO moneda values (41,'Metical');
INSERT INTO moneda values (42,'Dinar Tunecino');
/*MONEDA QUE NO ESTA ASIGNADA A NINGUN PAIS*/
INSERT INTO moneda values (43,'Rublo Ruso');
INSERT INTO moneda values (44,'Dong');

/*Monedas por pais EUROPA*/
INSERT INTO monedaXpais values (2,1,1);
INSERT INTO monedaXpais values (1,1,2);
INSERT INTO monedaXpais values (1,1,3);
INSERT INTO monedaXpais values (1,1,4);
INSERT INTO monedaXpais values (1,1,5);
INSERT INTO monedaXpais values (3,1,6);
INSERT INTO monedaXpais values (4,1,7);
INSERT INTO monedaXpais values (1,1,8);
INSERT INTO monedaXpais values (1,1,9);
INSERT INTO monedaXpais values (1,1,10);

/*Monedas por pais AMERICA*/
INSERT INTO monedaXpais values (5,2,1);
INSERT INTO monedaXpais values (6,2,2);
INSERT INTO monedaXpais values (7,2,3);
INSERT INTO monedaXpais values (8,2,4);
INSERT INTO monedaXpais values (9,2,5);
INSERT INTO monedaXpais values (10,2,6);
INSERT INTO monedaXpais values (5,2,6);
INSERT INTO monedaXpais values (11,2,7);
INSERT INTO monedaXpais values (12,2,8);
INSERT INTO monedaXpais values (13,2,9);
INSERT INTO monedaXpais values (14,2,10);

/*Monedas por pais OCEANIA*/
INSERT INTO monedaXpais values (15,3,1);
INSERT INTO monedaXpais values (16,3,2);
INSERT INTO monedaXpais values (5,3,3);
INSERT INTO monedaXpais values (17,3,4);
INSERT INTO monedaXpais values (18,3,5);
INSERT INTO monedaXpais values (19,3,6);
INSERT INTO monedaXpais values (15,3,6);
INSERT INTO monedaXpais values (20,3,7);
INSERT INTO monedaXpais values (15,3,8);
INSERT INTO monedaXpais values (21,3,9);
INSERT INTO monedaXpais values (22,3,10);

/*Monedas por pais ASIA*/
INSERT INTO monedaXpais values (23,4,1);
INSERT INTO monedaXpais values (24,4,2);
INSERT INTO monedaXpais values (25,4,3);
INSERT INTO monedaXpais values (26,4,4);
INSERT INTO monedaXpais values (27,4,5);
INSERT INTO monedaXpais values (28,4,6);
INSERT INTO monedaXpais values (29,4,7);
INSERT INTO monedaXpais values (30,4,8);
INSERT INTO monedaXpais values (31,4,9);
INSERT INTO monedaXpais values (32,4,10);

/*Monedas por pais AFRICA*/
INSERT INTO monedaXpais values (33,5,1);
INSERT INTO monedaXpais values (34,5,2);
INSERT INTO monedaXpais values (35,5,3);
INSERT INTO monedaXpais values (36,5,4);
INSERT INTO monedaXpais values (37,5,5);
INSERT INTO monedaXpais values (38,5,6);
INSERT INTO monedaXpais values (39,5,7);
INSERT INTO monedaXpais values (40,5,8);
INSERT INTO monedaXpais values (41,5,9);
INSERT INTO monedaXpais values (42,5,10);

INSERT INTO tasas values (1,2,to_date('18/08/2016','DD/MM/YYYY'),0.862051);
INSERT INTO tasas values (1,3,to_date('18/08/2016','DD/MM/YYYY'),9.47313);
INSERT INTO tasas values (1,4,to_date('18/08/2016','DD/MM/YYYY'),1.08332);
INSERT INTO tasas values (1,5,to_date('18/08/2016','DD/MM/YYYY'),1.13446);
INSERT INTO tasas values (1,6,to_date('18/08/2016','DD/MM/YYYY'),1.4498);
INSERT INTO tasas values (1,7,to_date('18/08/2016','DD/MM/YYYY'),20.6258);
INSERT INTO tasas values (1,8,to_date('18/08/2016','DD/MM/YYYY'),25.9737);
INSERT INTO tasas values (1,9,to_date('18/08/2016','DD/MM/YYYY'),143.978);
INSERT INTO tasas values (1,10,to_date('18/08/2016','DD/MM/YYYY'),1.13466);
INSERT INTO tasas values (1,11,to_date('18/08/2016','DD/MM/YYYY'),3303.32);
INSERT INTO tasas values (1,12,to_date('18/08/2016','DD/MM/YYYY'),11.2876);
INSERT INTO tasas values (1,13,to_date('19/08/2016','DD/MM/YYYY'),745.523);
INSERT INTO tasas values (1,14,to_date('19/08/2016','DD/MM/YYYY'),3.67574);
INSERT INTO tasas values (1,15,to_date('19/08/2016','DD/MM/YYYY'),1.47842);
INSERT INTO tasas values (1,16,to_date('19/08/2016','DD/MM/YYYY'),1.559213);
INSERT INTO tasas values (1,17,to_date('19/08/2016','DD/MM/YYYY'),118.969);
INSERT INTO tasas values (1,18,to_date('19/08/2016','DD/MM/YYYY'),3.5988);
INSERT INTO tasas values (1,19,to_date('19/08/2016','DD/MM/YYYY'),1.46055);
INSERT INTO tasas values (1,20,to_date('19/08/2016','DD/MM/YYYY'),2.83858);
INSERT INTO tasas values (1,21,to_date('19/08/2016','DD/MM/YYYY'),2.46125);
INSERT INTO tasas values (1,22,to_date('19/08/2016','DD/MM/YYYY'),8.85868);
INSERT INTO tasas values (1,23,to_date('19/08/2016','DD/MM/YYYY'),7.52367);
INSERT INTO tasas values (1,24,to_date('19/08/2016','DD/MM/YYYY'),52.5244);
INSERT INTO tasas values (1,25,to_date('19/08/2016','DD/MM/YYYY'),1257.53);
INSERT INTO tasas values (1,26,to_date('19/08/2016','DD/MM/YYYY'),1.52002);
INSERT INTO tasas values (1,27,to_date('19/08/2016','DD/MM/YYYY'),9193.02);
INSERT INTO tasas values (1,28,to_date('19/08/2016','DD/MM/YYYY'),4.52717);
INSERT INTO tasas values (1,29,to_date('19/08/2016','DD/MM/YYYY'),39.2105);
INSERT INTO tasas values (1,30,to_date('19/08/2016','DD/MM/YYYY'),113.767);
INSERT INTO tasas values (1,31,to_date('19/08/2016','DD/MM/YYYY'),4656.39);
INSERT INTO tasas values (1,32,to_date('19/08/2016','DD/MM/YYYY'),75.8179);
INSERT INTO tasas values (1,33,to_date('19/08/2016','DD/MM/YYYY'),3445.47);
INSERT INTO tasas values (1,34,to_date('19/08/2016','DD/MM/YYYY'),4.47499);
INSERT INTO tasas values (1,35,to_date('19/08/2016','DD/MM/YYYY'),10.0638);
INSERT INTO tasas values (1,36,to_date('19/08/2016','DD/MM/YYYY'),364.67);
INSERT INTO tasas values (1,37,to_date('19/08/2016','DD/MM/YYYY'),25.1279);
INSERT INTO tasas values (1,38,to_date('19/08/2016','DD/MM/YYYY'),114.891);
INSERT INTO tasas values (1,39,to_date('19/08/2016','DD/MM/YYYY'),655.957);
INSERT INTO tasas values (1,40,to_date('19/08/2016','DD/MM/YYYY'),10.9528);
INSERT INTO tasas values (1,41,to_date('19/08/2016','DD/MM/YYYY'),81.6616);
INSERT INTO tasas values (1,42,to_date('19/08/2016','DD/MM/YYYY'),2.48063);
INSERT INTO tasas values (2,1,to_date('19/08/2016','DD/MM/YYYY'),1.1596);
INSERT INTO tasas values (2,19,to_date('19/08/2016','DD/MM/YYYY'),1.68778);
INSERT INTO tasas values (3,1,to_date('19/08/2016','DD/MM/YYYY'),0.105562);
INSERT INTO tasas values (4,1,to_date('18/08/2016','DD/MM/YYYY'),0.923088);
INSERT INTO tasas values (5,1,to_date('18/08/2016','DD/MM/YYYY'),0.881477);
INSERT INTO tasas values (5,19,to_date('19/08/2016','DD/MM/YYYY'),1.29549);
INSERT INTO tasas values (6,1,to_date('18/08/2016','DD/MM/YYYY'),0.689752);
INSERT INTO tasas values (7,1,to_date('18/08/2016','DD/MM/YYYY'),0.0484829);
INSERT INTO tasas values (8,1,to_date('18/08/2016','DD/MM/YYYY'),0.0385005);
INSERT INTO tasas values (9,1,to_date('18/08/2016','DD/MM/YYYY'),0.00694549);
INSERT INTO tasas values (10,1,to_date('18/08/2016','DD/MM/YYYY'),0.881318);
INSERT INTO tasas values (11,1,to_date('18/08/2016','DD/MM/YYYY'),0.000302726);
INSERT INTO tasas values (12,1,to_date('18/08/2016','DD/MM/YYYY'),0.0885925);
INSERT INTO tasas values (13,1,to_date('19/08/2016','DD/MM/YYYY'),0.00134134);
INSERT INTO tasas values (14,1,to_date('19/08/2016','DD/MM/YYYY'),0.272054);
INSERT INTO tasas values (15,1,to_date('19/08/2016','DD/MM/YYYY'),0.676399);
INSERT INTO tasas values (16,1,to_date('19/08/2016','DD/MM/YYYY'),0.641384);
INSERT INTO tasas values (17,1,to_date('19/08/2016','DD/MM/YYYY'),0.0084056);
INSERT INTO tasas values (18,1,to_date('19/08/2016','DD/MM/YYYY'),0.27787);
INSERT INTO tasas values (19,1,to_date('19/08/2016','DD/MM/YYYY'),0.68467);
INSERT INTO tasas values (19,2,to_date('19/08/2016','DD/MM/YYYY'),0.59250);
INSERT INTO tasas values (19,5,to_date('19/08/2016','DD/MM/YYYY'),0.77191);
INSERT INTO tasas values (20,1,to_date('19/08/2016','DD/MM/YYYY'),0.352289);
INSERT INTO tasas values (21,1,to_date('19/08/2016','DD/MM/YYYY'),0.406298);
INSERT INTO tasas values (22,1,to_date('19/08/2016','DD/MM/YYYY'),0.112884);
INSERT INTO tasas values (23,1,to_date('19/08/2016','DD/MM/YYYY'),0.132914);
INSERT INTO tasas values (24,1,to_date('19/08/2016','DD/MM/YYYY'),0.0190388);
INSERT INTO tasas values (25,1,to_date('19/08/2016','DD/MM/YYYY'),0.000795208);
INSERT INTO tasas values (26,1,to_date('19/08/2016','DD/MM/YYYY'),0.657888);
INSERT INTO tasas values (27,1,to_date('19/08/2016','DD/MM/YYYY'),0.00010877);
INSERT INTO tasas values (28,1,to_date('19/08/2016','DD/MM/YYYY'),0.220888);
INSERT INTO tasas values (29,1,to_date('19/08/2016','DD/MM/YYYY'),0.0255034);
INSERT INTO tasas values (30,1,to_date('19/08/2016','DD/MM/YYYY'),0.00878992);
INSERT INTO tasas values (31,1,to_date('19/08/2016','DD/MM/YYYY'),0.0002147);
INSERT INTO tasas values (32,1,to_date('19/08/2016','DD/MM/YYYY'),0.0131895);
INSERT INTO tasas values (33,1,to_date('19/08/2016','DD/MM/YYYY'),0.00029023);
INSERT INTO tasas values (34,1,to_date('19/08/2016','DD/MM/YYYY'),0.223464);
INSERT INTO tasas values (35,1,to_date('19/08/2016','DD/MM/YYYY'),0.0993664);
INSERT INTO tasas values (36,1,to_date('19/08/2016','DD/MM/YYYY'),0.0027422);
INSERT INTO tasas values (37,1,to_date('19/08/2016','DD/MM/YYYY'),0.0397965);
INSERT INTO tasas values (38,1,to_date('19/08/2016','DD/MM/YYYY'),0.00870387);
INSERT INTO tasas values (39,1,to_date('19/08/2016','DD/MM/YYYY'),0.00152449);
INSERT INTO tasas values (40,1,to_date('19/08/2016','DD/MM/YYYY'),0.0913005);
INSERT INTO tasas values (41,1,to_date('19/08/2016','DD/MM/YYYY'),0.0122457);
INSERT INTO tasas values (42,1,to_date('19/08/2016','DD/MM/YYYY'),0.403123);
/*VALORES PARA DIFERENTES FECHAS*/
INSERT INTO tasas values (1,2,to_date('24/08/2016','DD/MM/YYYY'),0.850639);
INSERT INTO tasas values (1,3,to_date('24/08/2016','DD/MM/YYYY'),9.46049);
INSERT INTO tasas values (1,4,to_date('24/08/2016','DD/MM/YYYY'),1.08872);
INSERT INTO tasas values (2,1,to_date('24/08/2016','DD/MM/YYYY'),1.17559);
INSERT INTO tasas values (3,1,to_date('24/08/2016','DD/MM/YYYY'),0.105703);
INSERT INTO tasas values (4,1,to_date('24/08/2016','DD/MM/YYYY'),0.918514);
INSERT INTO tasas values (15,16,to_date('24/08/2016','DD/MM/YYYY'),1.04112);
INSERT INTO tasas values (16,15,to_date('24/08/2016','DD/MM/YYYY'),0.960501);


commit;
/*VISTAS UTILES*/
DROP VIEW continentePais;
DROP VIEW monedaPais;
DROP VIEW paisMonedaNombres;
CREATE VIEW continentePais as(
select descripcion, nombre, cod_pais
from continente natural join pais
);
CREATE VIEW monedaPais as(
select id_moneda, id_pais, nombre
from MONEDAXPAIS natural right outer join pais
where id_pais = cod_pais
);
CREATE VIEW paisMonedaNombres as(
select *
from monedaPais left outer join moneda
using(id_moneda)
);
select * from moneda
ORDER by id_moneda;
/* Punto 1: Monedas por continente */
/*MONEDAS: Rublo Ruso y Dong deben aparecer en 0 ya que no fueron asignadas a ningun pais*/


/* Punto 2: Moneda con tasas de cambio en todas las monedas */
select id_moneda, nombre
from moneda M
where not exists((select id_moneda
                    from moneda
                    where id_moneda <> M.id_moneda)
                  minus
                  (select id_moneda_destino
                    from tasas T
                    where T.id_moneda_origen = M.id_moneda));

/* Punto 3: Monedas que faltan tasas de cambio respecto a las demas monedas */
/*La Mayoria de monedas le faltan tasas de cambio con las demas monedas
  EXCEPTO EL EURO (NO DEBE APARECER EL EURO EN ESTA CONSULTA)*/


/* Punto 4: Porcentaje de aumento o disminucion de la tasa de cambio de cada combinacion */
/*Hay tasas de cambio con fechas diferentes
  ->EURO CON LA MONEDAS PRIMERAS
  ->DOLAR AUSTRALIANO con DOLAR NEOZELANDES*/ 
with tasaN(moneda_origen, moneda_destino, fecha_act, tasa_act) as 
          (select M.nombre, N.nombre, fecha, tasa_cambio
           from tasas, moneda M, moneda N
           where id_moneda_origen = M.id_moneda and id_moneda_destino = N.id_moneda
           group by M.nombre, N.nombre, fecha, tasa_cambio
           order by M.NOMBRE) 

select A.moneda_origen, A.moneda_destino, A.fecha_act, A.tasa_act, B.fecha_act, B.tasa_act, (((A.tasa_act / B.tasa_act) - 1) * 100)
from tasaN A, tasaN B
where (B.fecha_act = A.fecha_act - interval '1' day or B.fecha_act = A.fecha_act ) and 
       A.moneda_origen = B.moneda_origen and A.moneda_destino = B.moneda_destino
order by A.moneda_origen, A.fecha_act;
