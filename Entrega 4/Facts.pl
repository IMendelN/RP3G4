%----------------------------------
%seller(Vendedor_Imobiliaria)
%----------------------------------
seller('Maria', 'Alegrete').
seller('Paulo', 'Baita Chão').
seller('Ana', 'Ibirapuitã').
seller('José', 'Ibirapuitã').
seller('Lucas', 'Ibirapuitã').
seller('Marisa', 'Baita Chão').
seller('Rosa', 'Alegrete').
seller('Miguel', 'Alegrete').

%----------------------------------
%client(NrCliente_Idade_Profissão)
%----------------------------------
client(1010, 55, 'Veterinário').
client(2010, 60, 'Advogado').
client(3010, 25, 'Professor').
client(3011, 35, 'Professor').
client(1011, 53, 'Médico').
client(3012, 47, 'Médico').
client(2011, 45, 'Militar').
client(1012, 40, 'Militar').
client(2012, 39, 'Veterinário').
client(1013, 65, 'Advogado').

%----------------------------------
%seller_client(Vendedor_NrCliente)
%----------------------------------
seller_client('Maria', 1010).
seller_client('Paulo', 2010).
seller_client('Ana', 3010).
seller_client('José', 3011).
seller_client('Maria', 1011).
seller_client('Lucas', 3012).
seller_client('Marisa', 2011).
seller_client('Rosa', 1012).
seller_client('Paulo', 2012).
seller_client('Miguel', 1013).

%----------------------------------
%purchase(Vendedor_NrCliente_Imobiliária_MovCompra)
%----------------------------------
purchase('Maria', 1010, 'Alegrete', 'Casa 3 quartos por 300 mil reais').
purchase('Maria', 1010, 'Alegrete', 'Apartamento 1 quarto por 120 mil reais').
purchase('Paulo', 2010, 'Baita Chão', 'Casa 3 quartos por 700 reais').
purchase('Ana', 3010, 'Ibirapuitã', 'Apartamento 1 quarto por 130 mil reais').
purchase('José', 3011, 'Ibirapuitã', 'Casa 1 quarto por 220 mil reais').
purchase('Maria', 1011, 'Alegrete', 'Apartamento 3 quartos por 500 mil reais').
purchase('Lucas', 3012, 'Ibirapuitã', 'Casa 2 quartos por 250 mil reais').
purchase('Lucas', 3012, 'Ibirapuitã', 'Apartamento 2 quartos por 140 mil reais').
purchase('Marisa', 2011, 'Baita Chão', 'Apartamento 2 quartos por 250 mil reais').
purchase('Rosa', 1012, 'Alegrete', 'Apartamento 2 quartos por 300 mil reais').
purchase('Paulo', 2012, 'Baita Chão', 'Casa 2 quartos por 260 mil reais').
purchase('Paulo', 2012, 'Baita Chão', 'Casa 1 quarto por 150 mil reais').
purchase('Miguel', 1013, 'Alegrete', 'Apartamento 3 quartos por 650 mil reais').
purchase('Miguel', 1013, 'Alegrete', 'Apartamento 1 quarto por 145 mil reais').
purchase('Miguel', 1013, 'Alegrete', 'Apartamento 2 quartos por 160 mil reais').

%-----------------------------------
%sales(Tipo_Valor_Imobiliaria)
%-----------------------------------
sales('Casa', 300000, 'Alegrete').
sales('Apartamento', 120000, 'Alegrete').
sales('Casa', 700000, 'Baita Chão').
sales('Apartamento', 130000, 'Ibirapuitã').
sales('Casa', 220000, 'Ibirapuitã').
sales('Apartamento', 500000, 'Alegrete').
sales('Casa', 250000, 'Ibirapuitã').
sales('Apartamento', 140000, 'Ibirapuitã').
sales('Apartamento', 250000, 'Baita Chão').
sales('Apartamento', 300000, 'Alegrete').
sales('Casa', 260000, 'Baita Chão').
sales('Casa', 150000, 'Baita Chão').
sales('Apartamento', 650000, 'Alegrete').
sales('Apartamento', 145000, 'Alegrete').
sales('Apartamento', 160000, 'Alegrete').