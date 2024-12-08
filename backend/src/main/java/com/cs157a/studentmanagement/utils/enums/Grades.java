package com.cs157a.studentmanagement.utils.enums;

public enum Grades {
   A, A_MINUS, B_PLUS, B, B_MINUS, C_PLUS, C, C_MINUS, D, F, N_A;

   @Override
   public String toString() {
      switch (this) {
         case A_MINUS: return "A-";
         case B_PLUS: return "B+";
         case B_MINUS: return "B-";
         case C_PLUS: return "C+";
         case C_MINUS: return "C-";
         case N_A: return "N/A";
         default: return this.name();
      }
   }
}
