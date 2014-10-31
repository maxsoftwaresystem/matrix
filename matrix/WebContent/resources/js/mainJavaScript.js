function desabilitaBotao() {
	btnEditar.disable();
	btnExcluir.disable();
}

function abilitaBotao() {
	btnEditar.enable();
	btnExcluir.enable();
}

function selecionaLinha() {
	desabilitaBotao();
	dataTable.unselectAllRows();
}