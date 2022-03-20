* Listagem dos Itens:
curl --request GET \
    --url http://localhost:8080/items
Response:
    200:
    [
    	{
    		"_id": "cc88f19d-0648-4518-b0ab-519c1c7f669b",
    		"name": "Água",
    		"value": 2.0,
    		"created_at": "2022-03-08T13:20:19.435+00:00",
    		"updated_at": "2022-03-08T13:20:19.435+00:00"
    	},
    	{
    		"_id": "ad2d1795-ff26-41b7-be9e-c34b80deec28",
    		"name": "Arma",
    		"value": 4.0,
    		"created_at": "2022-03-08T13:20:19.435+00:00",
    		"updated_at": "2022-03-08T13:20:19.435+00:00"
    	},
    	{
    		"_id": "2be1df29-8a46-4221-91a0-9cb3c3f2e8ff",
    		"name": "Comida",
    		"value": 1.0,
    		"created_at": "2022-03-08T13:20:19.435+00:00",
    		"updated_at": "2022-03-08T13:20:19.435+00:00"
    	},
    	{
    		"_id": "53c5d39a-c430-4d73-a097-768783c6482d",
    		"name": "Munição",
    		"value": 3.0,
    		"created_at": "2022-03-08T13:20:19.435+00:00",
    		"updated_at": "2022-03-08T13:20:19.435+00:00"
    	}
    ]

 * Criação de Rebelde:
 curl --request POST \
    --url http://localhost:8080/rebels \
    --header 'Content-Type: application/json' \
    --data '{
        "name": "José",
        "age": 30,
        "genre": "gree",
        "location": {
            "latitude": 81,
            "longitude": -91,
            "galaxy_name": "Caixa prego"
        },
        "inventory": [
            {
                "item_id": "ad2d1795-ff26-41b7-be9e-c34b80deec28",
                "quantity": 4
            },
            {
                "item_id": "ad2d1795-ff26-41b7-be9e-c34b80deec28",
                "quantity": 5
            },
            {
                "item_id": "53c5d39a-c430-4d73-a097-768783c6482d",
                "quantity": 3
            },
            {
                "item_id": "cc88f19d-0648-4518-b0ab-519c1c7f669b",
                "quantity": 7
            },
            {
                "item_id": "2be1df29-8a46-4221-91a0-9cb3c3f2e8ff",
                "quantity": 12
            }
        ]
     }'
 Response:
    200:
    {
    	"_id": "bd3d6e82-e604-4ea7-929b-0e540116d70e",
    	"name": "José",
    	"age": 30,
    	"genre": "gree",
    	"location": {
    		"_id": "45364551-4e9a-4dba-8919-44aa9561462b",
    		"latitude": 81.0,
    		"longitude": -91.0,
    		"galaxy_name": "Caixa prego",
    		"created_at": "2022-03-18T03:10:12.171+00:00",
    		"updated_at": "2022-03-18T03:10:12.171+00:00"
    	},
    	"inventory": [
    		{
    			"_id": "ece34fff-1f87-425c-bb4a-028d0691f721",
    			"quantity": 12,
    			"item_id": "2be1df29-8a46-4221-91a0-9cb3c3f2e8ff",
    			"item_name": "Comida",
    			"created_at": "2022-03-18T03:10:12.173+00:00",
    			"updated_at": "2022-03-18T03:10:12.173+00:00"
    		},
    		{
    			"_id": "ac66c198-36a7-40e5-be8b-f124e5fee36b",
    			"quantity": 7,
    			"item_id": "cc88f19d-0648-4518-b0ab-519c1c7f669b",
    			"item_name": "Água",
    			"created_at": "2022-03-18T03:10:12.175+00:00",
    			"updated_at": "2022-03-18T03:10:12.175+00:00"
    		},
    		{
    			"_id": "5fd09b91-5014-4c56-94a3-23272a09cafe",
    			"quantity": 3,
    			"item_id": "53c5d39a-c430-4d73-a097-768783c6482d",
    			"item_name": "Munição",
    			"created_at": "2022-03-18T03:10:12.176+00:00",
    			"updated_at": "2022-03-18T03:10:12.176+00:00"
    		},
    		{
    			"_id": "eb097269-2722-40c1-8695-fc4d4edc78fa",
    			"quantity": 9,
    			"item_id": "ad2d1795-ff26-41b7-be9e-c34b80deec28",
    			"item_name": "Arma",
    			"created_at": "2022-03-18T03:10:12.178+00:00",
    			"updated_at": "2022-03-18T03:10:12.178+00:00"
    		}
    	],
    	"accused_count": 0,
    	"created_at": "2022-03-18T03:10:12.169+00:00",
    	"updated_at": "2022-03-18T03:10:12.169+00:00"
    }
    400: caso seja informado um item id inválido

 * Atualização de Localização do Rebelde:
 curl --request POST \
    --url http://localhost:8080/rebels/bd3d6e82-e604-4ea7-929b-0e540116d70e/locations \
    --header 'Content-Type: application/json' \
    --data '{
        "latitude": -93,
        "longitude": 23.81,
        "galaxy_name": "Buraco negro"
     }'
 Response:
    200:
    {
    	"_id": "2e9109e0-4686-4516-8095-ce4412864d89",
    	"latitude": -93.0,
    	"longitude": 23.81,
    	"galaxy_name": "Buraco negro",
    	"created_at": "2022-03-16T21:21:01.428+00:00",
    	"updated_at": "2022-03-16T21:21:01.428+00:00"
    }
    404: caso não seja encontrado um rebelde com o id informado

 * Denúncia de traidor:
 curl --request POST \
    --url http://localhost:8080/denunciations \
    --header 'Content-Type: application/json' \
    --data '{
        "accuser_rebel_id": "59feb07d-76cc-41d3-aedd-0adcf6beb816",
        "accused_rebel_id": "bb6fb2f8-1988-4259-a130-2a2c48bdb237"
     }'
 Response:
    200:
    {
    	"_id": "d436be21-7e2a-4c24-add0-5bcda8b426ab",
    	"accused_rebel": {
    		"_id": "bb6fb2f8-1988-4259-a130-2a2c48bdb237",
    		"name": "João"
    	},
    	"accuser_rebel": {
    		"_id": "59feb07d-76cc-41d3-aedd-0adcf6beb816",
    		"name": "José"
    	},
    	"created_at": "2022-03-15T16:59:38.446+00:00",
    	"updated_at": "2022-03-15T16:59:38.446+00:00"
    }
    400: caso já exista a denúncia (par acusador, acusado) ou tentativa de auto denúncia (acusador == acusado)
    404: caso o não seja encontrado o rebelde acusador ou o rebelde acusado com o id informado

 * Negociação de Items:
 curl --request POST \
    --url http://localhost:8080/deals \
    --header 'Content-Type: application/json' \
    --data '{
        "rebel1_id": "77eb28bd-5e2b-4acd-8600-1bcf4b847fca",
        "rebel2_id": "bd3d6e82-e604-4ea7-929b-0e540116d70e",
        "rebel1_items": [
            {
                "item_id": "cc88f19d-0648-4518-b0ab-519c1c7f669b",
                "quantity": 1
            }
        ],
        "rebel2_items": [
            {
                "item_id": "2be1df29-8a46-4221-91a0-9cb3c3f2e8ff",
                "quantity": 2
            }
        ]
     }'
 Response:
    200:
    {
    	"_id": "1c1b1272-52e0-475d-b835-a183a3fd704b",
    	"rebel1": {
    		"_id": "77eb28bd-5e2b-4acd-8600-1bcf4b847fca",
    		"name": "Geraldo"
    	},
    	"rebel2": {
    		"_id": "bd3d6e82-e604-4ea7-929b-0e540116d70e",
    		"name": "José"
    	},
    	"items": [
    		{
    			"_id": "c1635143-6402-4995-ac78-83be15891fbb",
    			"quantity": 1,
    			"item_id": "cc88f19d-0648-4518-b0ab-519c1c7f669b",
    			"item_name": "Água",
    			"source_rebel": {
    				"_id": "77eb28bd-5e2b-4acd-8600-1bcf4b847fca",
    				"name": "Geraldo"
    			},
    			"receiver_rebel": {
    				"_id": "bd3d6e82-e604-4ea7-929b-0e540116d70e",
    				"name": "José"
    			},
    			"created_at": "2022-03-18T03:16:19.621+00:00",
    			"updated_at": "2022-03-18T03:16:19.621+00:00"
    		},
    		{
    			"_id": "3a71d334-caff-492e-a72f-c148281b8b79",
    			"quantity": 2,
    			"item_id": "2be1df29-8a46-4221-91a0-9cb3c3f2e8ff",
    			"item_name": "Comida",
    			"source_rebel": {
    				"_id": "bd3d6e82-e604-4ea7-929b-0e540116d70e",
    				"name": "José"
    			},
    			"receiver_rebel": {
    				"_id": "77eb28bd-5e2b-4acd-8600-1bcf4b847fca",
    				"name": "Geraldo"
    			},
    			"created_at": "2022-03-18T03:16:19.634+00:00",
    			"updated_at": "2022-03-18T03:16:19.634+00:00"
    		}
    	],
    	"created_at": "2022-03-18T03:16:19.605+00:00",
    	"updated_at": "2022-03-18T03:16:19.605+00:00"
    }
    403: tentativa de negociação por um traidor
    404: algum dos rebeldes não foi encontrador pelo id informado
    400: caso o rebelde não tenha itens suficientes para a negociação, ou haja algum item id inválido,
        a quantidade de pontos dos itens não seja equivalente ou o rebelde esteja tentando negociar consigo mesmo
        (rebel1 == rebel2)

 * Relatório:
 curl --request GET \
   --url http://localhost:8080/reports
 Response:
    200:
    {
    	"rebels_percentage": 100.0,
    	"traitors_percentage": 0.0,
    	"average_items": [
    		{
    			"item_id": "2be1df29-8a46-4221-91a0-9cb3c3f2e8ff",
    			"item_name": "Comida",
    			"item_count": 24,
    			"rebel_count": 2,
    			"average_per_rebel": 12.0
    		},
    		{
    			"item_id": "cc88f19d-0648-4518-b0ab-519c1c7f669b",
    			"item_name": "Água",
    			"item_count": 14,
    			"rebel_count": 2,
    			"average_per_rebel": 7.0
    		},
    		{
    			"item_id": "53c5d39a-c430-4d73-a097-768783c6482d",
    			"item_name": "Munição",
    			"item_count": 6,
    			"rebel_count": 2,
    			"average_per_rebel": 3.0
    		},
    		{
    			"item_id": "ad2d1795-ff26-41b7-be9e-c34b80deec28",
    			"item_name": "Arma",
    			"item_count": 18,
    			"rebel_count": 2,
    			"average_per_rebel": 9.0
    		}
    	],
    	"points_lost_by_traitors": 0
    }