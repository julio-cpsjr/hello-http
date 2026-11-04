package br.com.projeto.infraestrutura.adapters.outbound;

import br.com.projeto.core.domain.EntidadePrincipal;
import br.com.projeto.core.ports.outbound.SaidaPort;

public class TecnologiaDeSalvarAdapter  implements SaidaPort {
    @Override
    public void salvar(EntidadePrincipal dados){
        System.out.println("Dados salvos fisicamente ");
    }
}
