(module
(type $_sig_i32ri32 (func (param i32) (result i32)))
(type $_sig_i32 (func (param i32)))
(type $_sig_ri32 (func (result i32)))
(type $_sig_void (func ))
(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(import "runtime" "print" (func $print (param i32)))
(import "runtime" "read" (func $read (result i32)))
(memory 2000)
(global $SP (mut i32) (i32.const 0)) ;; start of stack
(global $MP (mut i32) (i32.const 0)) ;; mark pointer
(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000641024-4
(start $init)
(func $init
(local $localsStart i32)
i32.const 0;; let this be the stack size needed (params+locals+2)*4
call $reserveStack  ;; returns old MP (dynamic link)
get_global $MP
i32.store
get_global $MP
get_global $SP
i32.store offset=4
get_global $MP
i32.const 8
i32.add
set_local $localsStart
get_local $localsStart
i32.const 0
 ;; estamos en a
i32.const 4
i32.mul
i32.add
i32.const 5
i32.store
get_local $localsStart
i32.const 1
 ;; estamos en i
i32.const 4
i32.mul
i32.add
i32.const 1
i32.store
block
loop
get_local $localsStart
i32.const 1
i32.const 4
i32.mul
i32.add
i32.load
get_local $localsStart
i32.const 0
i32.const 4
i32.mul
i32.add
i32.load
i32.lt_s
i32.eqz
br_if 1
get_local $localsStart
i32.const 2
 ;; estamos en b
i32.const 4
i32.mul
i32.add
i32.const 10
i32.store
get_local $localsStart
i32.const 2
i32.const 4
i32.mul
i32.add
i32.load
get_local $localsStart
i32.const 1
i32.const 4
i32.mul
i32.add
i32.load
i32.add
call $print
get_local $localsStart
i32.const 1
i32.const 4
i32.mul
i32.add
get_local $localsStart
i32.const 1
i32.const 4
i32.mul
i32.add
i32.load
i32.const 1
i32.add
i32.store
br 0
end
end
i32.const 1
if
get_local $localsStart
i32.const 2
 ;; estamos en b
i32.const 4
i32.mul
i32.add
i32.const 10000
i32.store
get_local $localsStart
i32.const 2
i32.const 4
i32.mul
i32.add
i32.load
i32.const 5
i32.add
call $print
end
get_local $localsStart
i32.const 2
 ;; estamos en c
i32.const 4
i32.mul
i32.add
i32.const 100
i32.store
get_local $localsStart
i32.const 2
i32.const 4
i32.mul
i32.add
get_local $localsStart
i32.const 2
i32.const 4
i32.mul
i32.add
i32.load
i32.const 1
i32.sub
i32.store
get_local $localsStart
i32.const 2
i32.const 4
i32.mul
i32.add
i32.load
get_local $localsStart
i32.const 0
i32.const 4
i32.mul
i32.add
i32.load
i32.add
call $print
get_local $localsStart
i32.const 0
i32.const 4
i32.mul
i32.add
i32.load
i32.const 2
i32.rem_s
block
block
block
br_table 1 2
end
i32.const 1
call $print
br 0
end
i32.const 0
call $print
br 0
end
call $freeStack
)
(func $reserveStack (param $size i32)
(result i32)
get_global $MP
get_global $SP
set_global $MP
get_global $SP
get_local $size
i32.add
set_global $SP
get_global $SP
get_global $NP
i32.gt_u
if
i32.const 3
call $exception
end
)
(func $freeStack (type $_sig_void)
get_global $MP
i32.load offset=4
set_global $SP
get_global $MP
i32.load
set_global $MP
)
(export "init" (func $init))
)
