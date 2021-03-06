package com.micperez.dataStuctures
sealed trait Tree[+A]
case class Leaf[+A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  //exercise 3.25
  def size[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l, r) => 1 + size(l) + size(r)
  }
  
  //exercise 3.26
  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(x) => x
    case Branch(l, r) => maximum(l) max maximum(r)
  }
  
  //exercise 3.27
  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(x) => 0
    case Branch(l, r) => 1 + (depth(l) max depth(r))
  }
  
  //exercise 3.28
  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(x) => Leaf(f(x))
    case Branch(l, r) => Branch(map(l)(f), map(r)(f))
  }
  
  //exercise 3.29
  def fold[A, B](t: Tree[A])(b: A => B)(f: (B, B) => B): B = t match {
    case Leaf(x) => b(x)
    case Branch(l, r) => f(fold(l)(b)(f), fold(r)(b)(f))
  }
  def sizeViaFold[A](t: Tree[A]): Int = fold(t)(x => 1)(1 + _ + _)
  def maximumViaFold(t: Tree[Int]): Int = fold(t)(x => x)(_ max _)
  def depthViaFold[A](t: Tree[A]): Int = fold(t)(x => 0)((l, r) => 1 + (l max r))
  def mapViaFold[A, B](t: Tree[A])(f: A => B): Tree[B] = fold(t)(x => Leaf(f(x)): Tree[B])((l, r) => Branch(l, r))
}