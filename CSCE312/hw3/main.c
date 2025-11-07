/*
3.5, 3.6, 3.7, 3.8, 3.18, 3.20, 3.24, 3.25, 3.32, 3.35, 3.38, 3.41
*/

//3.5
void decode1(long *xp, long *yp, long *zp){
    long x = *xp;
    long y = *yp;
    long z = *zp;
    *yp = x;
    *zp = y;
    *xp = z;
}

//3.6
/*
%rbx holds p, %rdx holds q
WHAT WILL BE STORED IN %rax?
leaq 9(%rdx), %rax -> %rax = q + 9
leaq (%rdx,%rbx), %rax -> %rax = q + p
leaq (%rdx,%rbx,3), %rax -> %rax = q + 3p
leaq 2(%rbx,%rbx,7), %rax -> %rax = 8p + 2
leaq 0xE(,%rdx,3), %rax -> %rax = 3q + 14
leaq 6(%rbx,%rdx,7), %rax -> %rax = p + 7q + 6
*/

//3.7
short scale3(short x, short y, short z){
    /*
    rbx = 10y
    rbx += z
    rbx += xy
    */
    short t = 10 * y + z + x * y;
    return t;
}

//3.8 
/*
-> <DESTINATION>, <VALUE>
addq %rcx,(%rax) -> 0x100, 0x100
subq %rdx,8(%rax) -> 0x108, 0xA8
imulq $16,(%rax,%rdx,8) -> 0x118, 0x110
incq 16(%rax) -> 0x110, 0x14
decq %rcx -> %rcx, 0x0
subq %rdx,%rax -> %rax, 0xFD
*/

//3.18
//study this later, got branches mixed up
//x -> %rdi, y -> %rsi, z -> %rdx
short test(short x, short y, short z){
    short val = y + z - x;
    if(z > 5){
        if(y > 2){
            val = x / z;//undo last 2 commands
        }else{
            val = x / y;
        }
    }else if(z < 3){
        val = z / y;
    }
    return val;
}

//3.20
//shift right = divide
#define OP / 
short arith(short x){
    return x OP 16;
}

//3.24
//a -> %rdi, b -> %rsi
//comp(b, a) -> jg -> a > b
short loop_while(short a, short b){
    short result = 0;
    while(a > b){
        result = result + (a * b);
        a = a - 1;
    }
    return result;
}

//3.25
//a -> %rdi, b -> %rsi
long loopwhile2(long a, long b){
    long result = b;
    while(b > 0){
        result = result * a;
        b = b - a;
    }
    return result;
}

//3.32
/*
Instruction State values (at beginning)
Label PC Instruction %rdi %rsi %rax %rsp *%rsp Description
M1 0x400560 callq 10 — — 0x7fffffffe820 — first(10) called
F1 0x400548 lea 10 — — 0x7fffffffe818 0x400565 first entry
F2 0x40054c sub 10 11 — 0x7fffffffe818 0x400565
F3 0x400550 callq 9 11 — 0x7fffffffe818 0x400565 last(9, 11) called
L1 0x400540 mov 9 11 — 0x7fffffffe810 0x400555 last entry
L2 0x400543 imul 9 11 9 0x7fffffffe810 0x400555
L3 0x400547 retq 9 11 99 0x7fffffffe810 0x400555 Return 99 from last
F4 0x400555 repz repq 9 11 99 0x7fffffffe818 0x400565 Return 99 from first
M2 0x400565 mov 9 11 99 0x7fffffffe820 — Resume main
*/

//3.35
//rfun stores parameter x in Register %rbx, which is used to compute the result
long rfun(unsigned long x) {
    if (x == 0){
        return 0;
    }
    unsigned long nx = x >> 2;
    long rv = rfun(nx);
    return x + rv;
}

//3.38
/*
1 sum_element:
2 leaq 0(,%rdi,8), %rdx -> %rdx, 8i 
3 subq %rdi, %rdx -> %rdx, 7i
4 addq %rsi, %rdx -> %rdx, 7i+j
5 leaq (%rsi,%rsi,4), %rax -> %rax, 5j
6 addq %rax, %rdi -> %rax, i + 5j
7 movq Q(,%rdi,8), %rax -> %rax, M[xQ + 8(5j+i)]
8 addq P(,%rdx,8), %rax -> %rax, M[xP + 8 (7i + j)] + previous Matrix value
9 ret

ref to matrix P is at offset 8 * (7i + j).
ref to matrix Q is at offset 8 * (5j + i).
Therefore, P has 7 columns, Q has 5, M = 5, N = 7
*/

//3.41
/*
A. 
Offset 0-7 -> p
Offset 8-9 -> s.x
Offset 10-11 -> s.y
Offset 12-19 -> next
B. It uses 20 bytes
C. void st_init(struct test *st)
st in %rdi
1 st_init:
2 movl 8(%rdi), %eax -> %eax, st->s.x
3 movl %eax, 10(%rdi) -> st->s.y, %eax
4 leaq 10(%rdi), %rax -> %rax, &(st->s.y)
5 movq %rax, (%rdi) -> st->p, %rax
6 movq %rdi, 12(%rdi) -> st->next, st
7 ret
*/

/*
commented out b/c no struct test
void st_init(struct test *st)
{
    st->s.y = st->s.x;
    st->p = &(st->s.y);
    st->next = st;
}
*/