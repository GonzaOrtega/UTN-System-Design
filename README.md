# TPA — Grupo nro. 8 (jm)
#Dominio

Comenzaremos definiendo que un atuendo es una combinación de prendas que tiene sentido usar juntas. Algunos ejemplos de atuendos podrían ser:
tus anteojos de sol favoritos, una remera de mangas cortas azul, el pantalón que te regaló tu mamá y una zapatillas converse.
un pañuelo verde, una remera de mangas largas rayada, un pantalón de jean y un par de botas de cuero.
una musculosa de mickey, una pollera amarilla y unas crocs.
Sin embargo, no todas las combinaciones son válidas, ya que se requiere cubrir mínimamente algunas partes del cuerpo y la superposición de las prendas no siempre tiene sentido. El sistema no debería sugerir que alguien se ponga dos pares de zapatos al mismo tiempo ni debería permitir que no se use ninguna prenda en el torso.

Además, por el momento solamente consideraremos prendas de verano, sin superposiciones. Es decir, no tendrá sentido por ahora que un usuario se ponga un buzo arriba de la remera, o una campera arriba de una camisa, por dar algunos ejemplos. Las prendas siempre serán, por ahora, una sola para cada categoría, es decir para cada parte del cuerpo

A su vez, cada prenda estará caracterizada por distintos atributos que servirán para identificarla, como por ejemplo qué tipo de prenda es, de qué tela está hecha o cuál es su color principal / secundario.

Las personas pueden acceder a sus prendas a través de un guardarropas, no puede haber prendas "sueltas". Cada usuario podría tener más de un guardarropas pero las prendas no pueden compartirse entre ellos.

Por último, un usuario deberá ser capaz de solicitarle a QuéMePongo distintos atuendos sugeridos para ponerse a partir de la ropa que posee en sus guardarropas.

#Requerimientos detallados

Para esta entrega se deberán satisfacer los siguientes requerimientos: 

1 - Creación de prendas, las cuales deben ser válidas, según al menos las siguientes reglas:
    a) debe saberse qué tipo de prenda es (remera de mangas cortas, short, zapatos de tacón, etc)
    b) debe saberse a qué categoría pertenece, la cual debe ser consistente con el tipo de prenda y la parte del cuerpo donde se utiliza o su función (parte superior, parte inferior, calzado y accesorios).
    c) debe ser de un tipo de tela, que debe ser consistente con el tipo de prenda: por ejemplo, no tiene sentido tener una campera de seda o una remera de cuero. 
    d) debe ser de un color primario asociado, y opcionalmente uno secundario y diferente del anterior
2 - Generar sugerencias de atuendos válidas, implementando un algoritmo que genere todas las combinaciones posibles de ropa. 
3 - Creación de múltiples usuarios, cada uno con distintas prendas y guardarropas
4 - Obtener sugerencias desde varios guardarropas, sin mezclar prendas entre un guardarropa y otro. Es decir, las prendas no se comparten entre los guardarropas.
