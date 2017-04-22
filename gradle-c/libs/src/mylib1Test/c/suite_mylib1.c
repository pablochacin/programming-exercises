#include <CUnit/Basic.h>
#include "gradle_cunit_register.h"
#include "test_mylib1.h"

int suite_init(void) {
    return 0;
}

int suite_clean(void) {
    return 0;
}

void gradle_cunit_register() {
    CU_pSuite pSuiteMath = CU_add_suite("mylib1 tests", suite_init, suite_clean);
    CU_add_test(pSuiteMath, "test1", test_1);
    CU_add_test(pSuiteMath, "test2", test_2);
}
