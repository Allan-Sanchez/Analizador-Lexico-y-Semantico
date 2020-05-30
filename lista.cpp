
// Tipo de dato TLista
typedef struct TNodo {
        int valor;
        struct TNodo *sig;
        } TLista;
        
int main()
{
    TLista *L ;
    for (int i = 1;i < 20; i++)
    {
        TLista *temp = (TLista *) malloc(sizeof(TLista));
        temp->valor = i;
        temp->sig = L;
        L = temp;
    }
    
    TLista *p = L;
    while (p != 0)
    {
        printf("Valor del Nodo : %i \n",p->valor);
        p = p->sig;
    }
}
